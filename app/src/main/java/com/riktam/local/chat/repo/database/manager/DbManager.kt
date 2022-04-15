package com.riktam.local.chat.repo.database.manager

import android.util.Log
import androidx.lifecycle.LiveData
import com.riktam.local.chat.AppApplication
import com.riktam.local.chat.repo.database.dao.ChatDao
import com.riktam.local.chat.repo.database.dao.GroupDao
import com.riktam.local.chat.repo.database.dao.UserDao
import com.riktam.local.chat.repo.database.dao.UserToGroupDao
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.entities.UserToGroupEntity
import com.riktam.local.chat.repo.datarepo.AuthData
import com.riktam.local.chat.util.LCustomPref
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class DbManager {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface injectDao {
        var chatDao: ChatDao
        var userDao: UserDao
        var groupDao: GroupDao
        var userToGroupDao: UserToGroupDao
    }

    private val daoFactory =
        EntryPointAccessors.fromApplication(AppApplication.getApplicationContext(),
            injectDao::class.java)

    inner class Chat() {
        fun getChats(toId: Long, fromId: Long): LiveData<List<ChatEntity>> {
            return daoFactory.chatDao.getChats(fromId, toId)
        }

        fun getGroupChats(groupdId: Long): LiveData<List<ChatEntity>> {
            return daoFactory.chatDao.getGroupChats(groupdId)
        }

        suspend fun addChats(chat: ChatEntity) {
            daoFactory.chatDao.addChat(chat)
        }

        suspend fun deleteChat(chat: ChatEntity) {
            daoFactory.chatDao.deleteChat(chat)
        }
    }

    inner class User() {
        suspend fun verifyUser(userId: String, password: String): AuthData {
            val user = daoFactory.userDao.getUserInfo(userId, password)
            if (user?.name == userId && user.password == password) {
                return AuthData(userId, user.uId, password, true, false)
            }
            return AuthData(userId, 0, password, false, false)
        }

        suspend fun addUSer(userName: String, password: String): AuthData {
            try {
                var id = System.currentTimeMillis()
                var user = UserEntity(id, userName, password)
                daoFactory.userDao.addUser(user)
                return AuthData(userName, id, password, true, true)
            } catch (e: Exception) {
                return AuthData(userName, 0, password, false, false)

            }
        }

        fun getAllUser(): LiveData<List<UserEntity>> {
            return daoFactory.userDao.getAllUsers(LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID,
                0))
        }

    }

    inner class Group {
        suspend fun getAllGroups(): LiveData<List<GroupEntity>> {
            val fromId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
            val userList = daoFactory.userToGroupDao.getUserGroup(fromId)
            return daoFactory.groupDao.getGroups(userList)
        }

        suspend fun addGroup(name: String) {
            try {
                val gid = System.currentTimeMillis()
                var entity = GroupEntity(gid,
                    name,
                    LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)!!)
                daoFactory.groupDao.addGroup(entity)
                val userId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
                UserToGroup().addUserToGroup(gid, userId)
            } catch (e: Exception) {
                Log.e("errorDb", "error occured")
            }
        }
    }

    inner class UserToGroup {
        suspend fun addUserToGroup(groupId: Long, userId: Long) {
            try {
                val grpAndUser = UserToGroupEntity(System.currentTimeMillis(), userId, groupId)
                daoFactory.userToGroupDao.addUser(grpAndUser)
            } catch (e: Exception) {
                Log.e("errorDb", "error occured")
            }
        }

        suspend fun removeUserFromGroup(userId: Long, groupId: Long) {
            try {
                daoFactory.userToGroupDao.removeUser(groupId, userId)
            } catch (e: Exception) {
                Log.e("errorDb", "error occured")
            }
        }
    }
}
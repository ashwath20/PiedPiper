package com.riktam.local.chat.repo.database.manager

import com.riktam.local.chat.AppApplication
import com.riktam.local.chat.repo.database.dao.ChatDao
import com.riktam.local.chat.repo.database.dao.GroupDao
import com.riktam.local.chat.repo.database.dao.UserDao
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.datarepo.AuthData
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
        //  var userToGroupDao: UserToGroupDao
    }

    private val daoFactory =
        EntryPointAccessors.fromApplication(AppApplication.getApplicationContext(),
            injectDao::class.java)

    inner class Chat() {
        suspend fun getChats(chatId: String): List<ChatEntity> {
            return daoFactory.chatDao.getChat(chatId)
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
                return AuthData(userId, password, true, false)
            }
            return AuthData(userId, password, false, false)
        }

        suspend fun addUSer(userName: String, password: String): AuthData {
            try {
                var user = UserEntity(System.currentTimeMillis(), userName, password)
                daoFactory.userDao.addUser(user)
                return AuthData(userName, password, true, true)
            } catch (e: Exception) {
                return AuthData(userName, password, false, false)

            }
        }

        suspend fun getAllUser(): List<UserEntity> {
            return daoFactory.userDao.getAllUsers()
        }

    }

    inner class Group {

    }

    inner class UserToGroup {

    }
}
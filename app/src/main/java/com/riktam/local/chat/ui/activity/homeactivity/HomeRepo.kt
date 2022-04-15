package com.riktam.local.chat.ui.activity.homeactivity

import androidx.lifecycle.LiveData
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.manager.DbManager
import com.riktam.local.chat.repo.wrappers.UserAndGroupData

class HomeRepo {
    suspend fun getUserList(manager: DbManager): LiveData<List<UserEntity>> {
        return manager.User().getAllUser()
    }

    suspend fun getGroupList(manager: DbManager): LiveData<List<GroupEntity>>{
        return manager.Group().getAllGroups()
    }
    suspend fun addGroup(manager:DbManager,name:String){
        manager.Group().addGroup(name)

    }


}
package com.riktam.local.chat.repo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity

@Dao
interface GroupDao {
    @Query("SELECT * FROM  GroupEntity where gId =:id")
    fun getGroups(id:String): LiveData<GroupEntity>

    @Insert
    suspend fun addGroup(group:GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)
}


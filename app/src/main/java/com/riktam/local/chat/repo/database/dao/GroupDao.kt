package com.riktam.local.chat.repo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.GroupEntity

@Dao
interface GroupDao {
    @Query("SELECT * FROM  GroupEntity where gId in (:id)")
    fun getGroups(id: List<Long>):LiveData<List<GroupEntity>>

    @Query("SELECT * FROM  GroupEntity ")
    fun getALlGroups(): LiveData<List<GroupEntity>>

    @Insert
    suspend fun addGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)
}


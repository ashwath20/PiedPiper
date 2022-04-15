package com.riktam.local.chat.repo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.UserToGroupEntity

@Dao
interface UserToGroupDao {
    @Query("SELECT groupId FROM UserToGroupEntity where userId=:userId ")
    suspend fun getUserGroup(userId: Long): List<Long>

    @Insert
    suspend fun addUser(userData: UserToGroupEntity)

    @Query("DELETE FROM UserToGroupEntity where groupId=:groupId and userId=:userId")
    suspend fun removeUser(groupId: Long, userId: Long)
}
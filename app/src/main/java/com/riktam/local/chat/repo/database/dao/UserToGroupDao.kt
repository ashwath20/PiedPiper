package com.riktam.local.chat.repo.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.UserToGroupEntity

@Dao
interface UserToGroupDao {
    @Query("SELECT * FROM UserToGroupEntity where userId=:userId ")
    suspend fun getUserGroup(userId:String):List<UserToGroupEntity>
}
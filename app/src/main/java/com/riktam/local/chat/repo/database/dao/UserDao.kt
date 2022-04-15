package com.riktam.local.chat.repo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROm UserEntity where uId!=:selfId")
    fun getAllUsers(selfId: Long): LiveData<List<UserEntity>>

    @Query("SELECT * from UserEntity where name=:uName and password=:password LIMIT 1")
    suspend fun getUserInfo(uName: String, password: String): UserEntity?

    @Insert
    suspend fun addUser(use: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)
}



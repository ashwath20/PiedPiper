package com.riktam.local.chat.repo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatEntity where chatId =:id")
    suspend fun getChat(id:String): List<ChatEntity>

    @Insert
    suspend fun addChat(use: ChatEntity)

    @Delete
    suspend fun deleteChat(user: ChatEntity)
}


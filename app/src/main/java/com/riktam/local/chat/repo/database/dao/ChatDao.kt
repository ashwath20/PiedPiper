package com.riktam.local.chat.repo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.riktam.local.chat.repo.database.entities.ChatEntity

@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatEntity where (`to`=:toId  and `from`=:fromId) or (`to`=:fromId and `from`=:toId)")
    fun getChats(fromId: Long, toId: Long): LiveData<List<ChatEntity>>

    @Query("SELECT * FROM ChatEntity where (`to`=:groupId  or `from`=:groupId) and isGroup==:isGroup")
    fun getGroupChats(groupId: Long,isGroup:Boolean=true): LiveData<List<ChatEntity>>

    @Insert
    suspend fun addChat(use: ChatEntity)

    @Delete
    suspend fun deleteChat(user: ChatEntity)
}


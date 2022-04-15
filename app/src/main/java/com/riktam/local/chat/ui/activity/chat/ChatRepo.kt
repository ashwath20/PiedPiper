package com.riktam.local.chat.ui.activity.chat

import androidx.lifecycle.LiveData
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.manager.DbManager
import javax.inject.Inject

class ChatRepo @Inject constructor(val dbManger: DbManager) {
    fun getChats(toId: Long, fromId: Long): LiveData<List<ChatEntity>> {
        return dbManger.Chat().getChats(toId, fromId)
    }

    fun getGroupData(groupData:Long): LiveData<List<ChatEntity>> {
        return dbManger.Chat().getGroupChats(groupData)
    }

    suspend fun sendChat(message: String, toId: Long, fromId: Long,isGroup:Boolean) {
        dbManger.Chat().addChats(ChatEntity(System.currentTimeMillis(), message, fromId, toId,isGroup))
    }

}
package com.riktam.local.chat.ui.activity.chat

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.util.LCustomPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChatDataModel @Inject constructor(val chatRepo: ChatRepo) : ViewModel() {
    var chatData = MediatorLiveData<List<ChatEntity>>()

    fun getChatData(toId: Long, isGroup: Boolean) {
        if (isGroup) {
            val fromId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
            chatData.apply {
                addSource(chatRepo.getGroupData(toId)) {
                    value = it
                }
            }
        } else {
            val fromId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
            chatData.apply {
                addSource(chatRepo.getChats(toId, fromId)) {
                    value = it
                }
            }
        }

    }

    fun sendMessage(message: String, toId: Long, isGroup: Boolean) {
        var curId = LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID, 0)
        viewModelScope.launch {
            chatRepo.sendChat(message, toId, curId, isGroup)
        }

    }
}
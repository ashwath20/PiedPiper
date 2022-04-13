package com.riktam.local.chat.repo.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChatEntity")
class ChatEntity() {
    @PrimaryKey
    var chatId: Int = 0

    @ColumnInfo(name = "message")
    var message: String = ""

    @ColumnInfo(name = "from")
    var from: String = ""

    @ColumnInfo(name = "to")
    var to: String = ""
}
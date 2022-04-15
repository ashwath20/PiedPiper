package com.riktam.local.chat.repo.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ChatEntity")
class ChatEntity(
    @PrimaryKey
    var chatId: Long = 0,

    @ColumnInfo(name = "message")
    var message: String = "",

    @ColumnInfo(name = "from")
    var from: Long = 0,

    @ColumnInfo(name = "to")
    var to: Long = 0,

    @ColumnInfo(name = "isGroup")
    var isGroup: Boolean = false,
) {

}
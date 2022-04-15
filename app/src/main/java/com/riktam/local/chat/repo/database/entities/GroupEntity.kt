package com.riktam.local.chat.repo.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GroupEntity")
data class GroupEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "gId")
    var gId: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "owner")
    var owner: Long = 0
) {

}
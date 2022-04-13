package com.riktam.local.chat.repo.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "UserEntity",indices = [Index(value = ["name"],
    unique = true)])
data class UserEntity(
    @PrimaryKey
    var uId: Long,


    @ColumnInfo(name = "name")
    var name: String ,

    @ColumnInfo(name = "password")
    var password: String ,
) {


}
package com.riktam.local.chat.repo.database.entities

import androidx.room.*


@Entity(tableName = "UserToGroupEntity", foreignKeys = arrayOf(ForeignKey(entity = UserEntity::class,

    parentColumns = arrayOf("uId"),
    childColumns = arrayOf("userId"),
    onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = GroupEntity::class,
        parentColumns = arrayOf("gId"),
        childColumns = arrayOf("groupId"),
        onDelete = ForeignKey.CASCADE)))
data class UserToGroupEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "userId")
    var userId: String = "",
    @ColumnInfo(name = "groupId")
    var groupId: String = "",
) {


}
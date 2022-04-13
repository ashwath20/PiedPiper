package com.riktam.local.chat.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.riktam.local.chat.repo.database.dao.ChatDao
import com.riktam.local.chat.repo.database.dao.GroupDao
import com.riktam.local.chat.repo.database.dao.UserDao
import com.riktam.local.chat.repo.database.dao.UserToGroupDao
import com.riktam.local.chat.repo.database.entities.ChatEntity
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.entities.UserToGroupEntity

//, autoMigrations = [
//AutoMigration(from = 1, to = 2)
//],




@Database(
    entities = [UserEntity::class, GroupEntity::class, ChatEntity::class,UserToGroupEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun groupDao(): GroupDao
    abstract fun chatDao(): ChatDao
    abstract fun  userToGroupDao(): UserToGroupDao
}



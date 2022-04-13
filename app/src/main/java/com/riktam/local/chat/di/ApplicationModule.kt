package com.riktam.local.chat.di

import android.content.Context
import androidx.room.Room
import com.riktam.local.chat.repo.database.AppDatabase
import com.riktam.local.chat.repo.database.dao.ChatDao
import com.riktam.local.chat.repo.database.dao.GroupDao
import com.riktam.local.chat.repo.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideDbInstance(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "AppDatabase"
        )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideChatDao(db: AppDatabase): ChatDao = db.chatDao()

    @Provides
    fun provideGroupDao(db: AppDatabase): GroupDao = db.groupDao()

    //@Provides
    //fun provideUserToGroupDao(db: AppDatabase): UserToGroupDao = db.userToGroupDao()

}
package com.riktam.local.chat.di

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.room.Room
import com.riktam.local.chat.repo.database.AppDatabase
import com.riktam.local.chat.repo.database.dao.ChatDao
import com.riktam.local.chat.repo.database.dao.GroupDao
import com.riktam.local.chat.repo.database.dao.UserDao
import com.riktam.local.chat.repo.database.dao.UserToGroupDao
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.manager.DbManager
import com.riktam.local.chat.repo.wrappers.UserAndGroupData
import com.riktam.local.chat.util.LCustomPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton

    fun provideDbInstance(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "AppDatabase"
        )
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton

    fun provideChatDao(db: AppDatabase): ChatDao = db.chatDao()

    @Provides
    @Singleton

    fun provideGroupDao(db: AppDatabase): GroupDao = db.groupDao()

    @Singleton
    @Provides
    fun provideUserToGroupDao(db: AppDatabase): UserToGroupDao = db.userToGroupDao()

    @Provides
    @Singleton

    fun provideDbManager(): DbManager {
        return DbManager()
    }

    @Provides
    @Singleton
    fun provideUserData(): LiveData<List<UserEntity>> {
        Log.i("gotit", "calling hilt db users")
        return DbManager().User().getAllUser()
    }

//    @Provides
//    @Singleton
//    fun provideGroupData(): LiveData<List<GroupEntity>> {
//        Log.i("gotit", "calling hilt db group")
//        return DbManager().Group().getAllGroups()
//    }

    }

//    @Provides
//    @Singleton
//    fun provideMediatorData(
//        groupData: LiveData<List<GroupEntity>>,
//        userData: LiveData<List<UserEntity>>,
//    ): MediatorLiveData<List<UserAndGroupData>> {
//
//        return MediatorLiveData<List<UserAndGroupData>>().apply {
//            addSource(groupData) {
//                value = it.map {
//                    UserAndGroupData(it.name,
//                        "",
//                        false,
//                        it.gId,
//                        false,
//                        )
//                }
//            }
//
//            addSource(userData) {
//                value = it.map {
//                    UserAndGroupData(it.name,
//                        "",
//                        false,
//                        it.uId,
//                        it.uId == LCustomPref.getLongPref(LCustomPref.PREF_CURRENT_USER_ID,
//                            0))
//                }
//
//
//            }
//
//        }
//    }


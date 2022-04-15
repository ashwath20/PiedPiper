package com.riktam.local.chat.di

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.riktam.local.chat.repo.database.entities.GroupEntity
import com.riktam.local.chat.repo.database.entities.UserEntity
import com.riktam.local.chat.repo.database.manager.DbManager
import com.riktam.local.chat.repo.wrappers.UserAndGroupData
import com.riktam.local.chat.ui.activity.chat.ChatRepo
import com.riktam.local.chat.ui.activity.homeactivity.HomeRepo
import com.riktam.local.chat.util.LCustomPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideHomeRepo(): HomeRepo {
        return HomeRepo()
    }

    @Provides
    fun provideChatRepo(dbManager: DbManager): ChatRepo {
        return ChatRepo(dbManager)
    }




}
package com.riktam.local.chat

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class AppApplication: Application() {
    companion object {
       private lateinit var context: Context
       fun getApplicationContext():Context{
           return context;
       }
    }
    override fun onCreate() {
        super.onCreate()
        context=this
    }
}
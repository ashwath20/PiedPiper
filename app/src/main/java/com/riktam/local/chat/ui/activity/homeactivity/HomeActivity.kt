package com.riktam.local.chat.ui.activity.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.riktam.local.chat.R
import com.riktam.local.chat.repo.database.manager.DbManager

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
      //  DbManager.Chat().getChats()
    }
}
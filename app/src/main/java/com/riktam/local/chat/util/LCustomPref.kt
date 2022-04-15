package com.riktam.local.chat.util

import android.content.Context
import android.content.SharedPreferences
import com.riktam.local.chat.AppApplication
import com.riktam.local.chat.BuildConfig


object LCustomPref {
    val PREF_CURRENT_USER: String = "current_user"
    val PREF_CURRENT_USER_ID: String = "current_user_id"

    val PREF_IS_AUTHENTICATED:String="is_authenticated"




    var sharedPreferences = AppApplication.getApplicationContext()
        .getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)


    fun setPref(prefKey: String?, value: Int) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(prefKey, value)
        editor.apply()
    }

    fun setPref(prefKey: String?, value: Long) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong(prefKey, value)
        editor.apply()
    }

    fun setPref(prefKey: String?, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(prefKey, value)
        editor.apply()
    }

    fun setPref(prefKey: String?, value: String?) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(prefKey, value)
        editor.apply()
    }

    fun getIntPref(prefKey: String?, defaultValue: Int): Int {
        return sharedPreferences.getInt(prefKey, defaultValue)
    }

    fun getLongPref(prefKey: String?, defaultValue: Long): Long {
        return sharedPreferences.getLong(prefKey, defaultValue)
    }

    fun getBooleanPref(prefKey: String?, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(prefKey, defaultValue)
    }

    fun getStringPref(prefKey: String?, defaultValue: String?): String? {
        return sharedPreferences.getString(prefKey, defaultValue)
    }


}
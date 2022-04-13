package com.riktam.local.chat.ui.activity.authActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riktam.local.chat.repo.database.manager.DbManager
import com.riktam.local.chat.repo.datarepo.AuthData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    var userAuthViewModel = MutableLiveData<AuthData>()
    fun verifyUser(userId: String, pass: String) {
        val user = DbManager().User()
        viewModelScope.launch {
            val authData = user.verifyUser(userId, pass)
            userAuthViewModel.postValue(authData)
        }
    }

    fun addUser(userId: String, pass: String) {
        val user = DbManager().User()
        viewModelScope.launch {
            val authData = user.addUSer(userId, pass)
            userAuthViewModel.postValue(authData)
        }
    }
    fun getAllUser(){
        val user = DbManager().User()

        viewModelScope.launch {
            val authData = user.getAllUser()
            Log.i("usersSize","${authData.size}")
           // userAuthViewModel.postValue(AuthData(authData.name, authData.password, true, true))
        }
    }

}
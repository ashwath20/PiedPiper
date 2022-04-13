package com.riktam.local.chat.di

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveDataUsers @Inject constructor()  :ViewModel(){
    fun getUsers(){

    }

}
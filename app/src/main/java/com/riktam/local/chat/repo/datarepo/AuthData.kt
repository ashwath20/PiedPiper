package com.riktam.local.chat.repo.datarepo

data class AuthData(var userName:String,var userId:Long=0,var password:String,var isAuthenticated:Boolean,val isNew:Boolean) {

}
package com.riktam.local.chat.util

object LUtil {
    fun cleanUserInput(userName: String, password: String, repass: String?=null): Boolean {
//TODO:We can add more input verication like password length and type of char
        if (repass == null)
            return (!userName.isEmpty() && !password.isEmpty())
        else {
            return (!userName.isEmpty() && !password.isEmpty() && !repass.isEmpty() && repass.equals(
                password))
        }
    }
}
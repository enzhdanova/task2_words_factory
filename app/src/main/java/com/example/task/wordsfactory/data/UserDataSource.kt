package com.example.task.wordsfactory.data

import android.content.SharedPreferences
import com.example.task.wordsfactory.data.model.UserLogin
import javax.inject.Inject

class UserDataSource @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun login(name: String, email: String, password: String) {

        with(sharedPreferences.edit()) {
            putString("userName", name)
            putString("userEmail", email)
            putString("userPassword", password)
            apply()
        }
    }

    fun getUser(): UserLogin {
        val name = sharedPreferences.getString("userName","")?: ""
        val email = sharedPreferences.getString("userEmail", "")?: ""
        return UserLogin(name, email)
    }
}
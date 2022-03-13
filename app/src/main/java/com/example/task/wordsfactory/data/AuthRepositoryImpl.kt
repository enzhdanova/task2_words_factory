package com.example.task.wordsfactory.data

import android.content.SharedPreferences
import com.example.task.wordsfactory.data.model.UserLogin
import com.example.task.wordsfactory.ui.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) : AuthRepository {
    override fun login(name: String, email: String, password: String) {

        with(sharedPreferences.edit()) {
            putString("userName", name)
            putString("userEmail", email)
            putString("userPassword", password)
            apply()
        }
    }

    override fun getUser(): UserLogin {
        val name = sharedPreferences.getString("userName","")?: ""
        val email = sharedPreferences.getString("userEmail", "")?: ""
        return UserLogin(name, email)
    }
}
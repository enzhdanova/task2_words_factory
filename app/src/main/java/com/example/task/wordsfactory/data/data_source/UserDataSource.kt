package com.example.task.wordsfactory.data.data_source

import android.content.SharedPreferences
import com.example.task.wordsfactory.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_EMAIL = "userEmail"
        private const val USER_PASSWORD = "userPassword"
    }

    suspend fun login(name: String, email: String, password: String) {
        withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                putString(USER_NAME, name)
                putString(USER_EMAIL, email)
                putString(USER_PASSWORD, password)
                apply()
            }
        }
    }

    suspend fun getUser(): User? {
        return withContext(Dispatchers.IO) {
            val name = sharedPreferences.getString(USER_NAME, "") ?: ""
            val email = sharedPreferences.getString(USER_EMAIL, "") ?: ""

            if (name.isNotEmpty() && email.isNotEmpty()) {
                User(name, email)
            } else {
                null
            }
        }
    }

    suspend fun removeUser() {
        return withContext(Dispatchers.IO) {
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
        }
    }
}
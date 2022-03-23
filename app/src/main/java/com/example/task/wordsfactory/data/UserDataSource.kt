package com.example.task.wordsfactory.data

import android.content.SharedPreferences
import com.example.task.wordsfactory.data.model.User
import com.example.task.wordsfactory.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_EMAIL = "userEmail"
        private const val USER_PASSWORD = "userPassword"
    }

    suspend fun login(name: String, email: String, password: String) {
        withContext(ioDispatcher) {
            with(sharedPreferences.edit()) {
                putString(USER_NAME, name)
                putString(USER_EMAIL, email)
                putString(USER_PASSWORD, password)
                apply()
            }
        }
    }

    suspend fun getUser(): User {
        return withContext(ioDispatcher) {
            val name = sharedPreferences.getString(USER_NAME, "") ?: ""
            val email = sharedPreferences.getString(USER_EMAIL, "") ?: ""
            User(name, email, "")
        }
    }
}
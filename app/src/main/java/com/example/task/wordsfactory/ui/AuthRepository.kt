package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.Result
import com.example.task.wordsfactory.ui.viewmodel.entity.User

interface AuthRepository {
    suspend fun login(name: String, email: String, password: String) : Result<String>
    suspend fun getUser(): Result<User>
}
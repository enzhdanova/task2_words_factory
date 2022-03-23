package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.model.User

interface AuthRepository {
    suspend fun login(name: String, email: String, password: String) : Result<String>
    suspend fun getUser(): Result<User>
}
package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.model.UserLogin

interface AuthRepository {
    fun login(name: String, email: String, password: String)
    fun getUser(): UserLogin
}
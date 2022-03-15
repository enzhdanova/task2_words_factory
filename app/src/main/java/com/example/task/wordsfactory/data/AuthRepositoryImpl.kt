package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.UserLogin
import com.example.task.wordsfactory.ui.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: UserDataSource) : AuthRepository {
    override fun login(name: String, email: String, password: String) {

        dataSource.login(name = name, email = email, password = password)
    }

    override fun getUser(): UserLogin {
        return dataSource.getUser()
    }
}
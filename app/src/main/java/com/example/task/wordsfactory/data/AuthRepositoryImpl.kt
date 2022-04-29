package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.User
import com.example.task.wordsfactory.ui.AuthRepository
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : AuthRepository {

    override suspend fun login(name: String, email: String, password: String): Result<Boolean> {
        return try {
            dataSource.login(name = name, email = email, password = password)
            Result.success(true)
        } catch (ioe: IOException) {
            Result.failure(Exception(ioe))
        }
    }

    override suspend fun getUser(): Result<User> {
        return try {
            val user = dataSource.getUser()
            Result.success(User(name = user.name, email = user.email))
        } catch (ioe: IOException) {
            Result.failure(Exception(ioe))
        }
    }
}
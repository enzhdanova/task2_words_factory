package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.AuthRepository
import com.example.task.wordsfactory.ui.viewmodel.entity.User
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : AuthRepository {

    private val errorMessage = "Возникла проблема при получении данных"
    private val successMessage = "Success"

    override suspend fun login(name: String, email: String, password: String): Result<String> {
        return try {
            dataSource.login(name = name, email = email, password = password)
            Result.success(successMessage)
        } catch (ioe: IOException) {
            Result.failure(Exception(errorMessage))
        }

    }

    override suspend fun getUser(): Result<User> {
        return try {
            val user = dataSource.getUser()
            Result.success(User(name = user.name, email = user.email, password = ""))
        } catch (ioe: IOException) {
            Result.failure(Exception(errorMessage))
        }
    }
}
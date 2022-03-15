package com.example.task.wordsfactory.data

import android.content.Context
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.AuthRepository
import com.example.task.wordsfactory.ui.viewmodel.entity.User
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val context: Context,
    private val dataSource: UserDataSource
    ) : AuthRepository {
    override suspend fun login(name: String, email: String, password: String) : Result<String> {
        return try {
            dataSource.login(name = name, email = email, password = password)
            Result.Success(context.getString(R.string.success))
        } catch (ioe: IOException) {
            Result.Error(Exception(context.getString(R.string.error_message)))
        }

    }

    override suspend fun getUser(): Result<User> {
        return try {
            val user = dataSource.getUser()
            Result.Success(User(name = user.name, email = user.email, password = ""))
        }  catch (ioe: IOException) {
            Result.Error(Exception(context.getString(R.string.error_message)))
        }
    }
}
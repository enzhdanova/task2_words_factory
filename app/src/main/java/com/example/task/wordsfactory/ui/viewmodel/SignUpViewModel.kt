package com.example.task.wordsfactory.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.Result
import com.example.task.wordsfactory.ui.AuthRepository
import com.example.task.wordsfactory.ui.viewmodel.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val context: Context,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<SignUpUiState>()
    val uiState: LiveData<SignUpUiState>
        get() = _uiState

    fun login(name: String, email: String, password: String) {
        _uiState.value = SignUpUiState(
            name = name,
            email = email,
            password = password
        )
        val user = uiState.value
        println(user)

        if (user?.name == "" || user?.email == "" || user?.password == "") {
            _uiState.value = _uiState.value?.copy(
                error = true,
                errorMessage = context.getString(R.string.enter_data_error),
                successLogin = false
            )
        } else {
            saveUser(User(name, email, password))
        }
    }

    private fun saveUser(user: User) {
        viewModelScope.launch {
            when (val result = authRepository.login(user.name, user.email, user.password)) {
                is Result.Success<String> -> {
                    _uiState.value = _uiState.value?.copy(successLogin = true)
                }
                is Result.Error -> {
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = result.exception.message ?: ""
                    )
                }
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            when (val result = authRepository.getUser()) {
                is Result.Success<User> -> {
                    val user = result.data
                    _uiState.value = SignUpUiState(
                        name = user.name,
                        email = user.email,
                        successLogin = true
                    )
                }
                is Result.Error ->
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = result.exception.message ?: ""
                    )
            }
        }
    }


}
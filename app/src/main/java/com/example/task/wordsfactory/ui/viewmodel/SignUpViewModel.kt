package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.AppRegexp
import com.example.task.wordsfactory.ui.AuthRepository
import com.example.task.wordsfactory.ui.viewmodel.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val errorMessage = "Некоректные данные"

    private val _uiState = MutableLiveData<SignUpUiState>()
    val uiState: LiveData<SignUpUiState>
        get() = _uiState

    fun login(name: String, email: String, password: String) {
        _uiState.value = SignUpUiState(
            name = name,
            email = email,
            password = password
        )

        val nameCorrect = AppRegexp.correct(AppRegexp.REGEXP_NAME, name)
        val emailCorrect = AppRegexp.correct(AppRegexp.REGEXP_EMAIL, email)
        val passwordCorrect = AppRegexp.correct(AppRegexp.REGEXP_PASSWORD, password)

        if (nameCorrect && emailCorrect && passwordCorrect) {
            saveUser(User(name, email, password))

        } else {
            _uiState.value = _uiState.value?.copy(
                error = true,
                errorMessage = errorMessage,
                successLogin = false
            )
        }
    }

    private fun saveUser(user: User) {
        viewModelScope.launch {
            val result = authRepository.login(user.name, user.email, user.password)
            if (result.isSuccess) {
                _uiState.value = _uiState.value?.copy(successLogin = true)
            } else {
                result.onFailure {
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = it.message ?: ""
                    )
                }
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val result = authRepository.getUser()
            if (result.isSuccess) {
                result.onSuccess {
                    _uiState.value = SignUpUiState(
                        name = it.name,
                        email = it.email,
                        successLogin = true
                    )
                }
            } else {
                result.onFailure {
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = it.message ?: ""
                    )
                }
            }
        }
    }
}
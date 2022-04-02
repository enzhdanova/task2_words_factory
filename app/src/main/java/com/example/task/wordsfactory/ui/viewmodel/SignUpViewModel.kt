package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.RegexpUtils
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<SignUpUiState>()
    val uiState: LiveData<SignUpUiState>
        get() = _uiState

    init {
        getUser()
    }

    fun login(name: String, email: String, password: String) {
        _uiState.value = SignUpUiState(
            name = name,
            email = email,
            password = password
        )

        val nameCorrect = RegexpUtils.correct(RegexpUtils.REGEXP_NAME, name)
        val emailCorrect = RegexpUtils.correct(RegexpUtils.REGEXP_EMAIL, email)
        val passwordCorrect = RegexpUtils.correct(RegexpUtils.REGEXP_PASSWORD, password)

        if (nameCorrect && emailCorrect && passwordCorrect) {
            saveUser(name, email, password)
        } else {
            _uiState.value = _uiState.value?.copy(
                error = true,
                errorMessage = R.string.enter_data_error,
                successLogin = false
            )
        }
    }

    private fun saveUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            val result = authRepository.login(name, email, password)

            result.fold(
                onSuccess = {
                    _uiState.value = _uiState.value?.copy(successLogin = true)
                },
                onFailure = {
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = R.string.problem_with_get_data_error
                    )
                }

            )
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val result = authRepository.getUser()

            result.fold(
                onSuccess = {
                    _uiState.value = SignUpUiState(
                        name = it.name,
                        email = it.email,
                        successLogin = true
                    )
                },
                onFailure = {
                    _uiState.value = SignUpUiState(
                        error = true,
                        errorMessage = R.string.problem_with_get_data_error
                    )
                }
            )
        }
    }
}
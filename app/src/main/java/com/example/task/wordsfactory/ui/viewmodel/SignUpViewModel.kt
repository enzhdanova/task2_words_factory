package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.AuthRepositoryImpl
import com.example.task.wordsfactory.data.model.UserLogin
import com.example.task.wordsfactory.ui.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun editTextNameChanged(newText: String) {
        _uiState.update {
            it.copy(name = newText)
        }
    }

    fun editTextEmailChanged(newText: String) {
        _uiState.update {
            it.copy(e_mail = newText)
        }
    }

    fun editTextPasswordChanged(newText: String) {
        _uiState.update {
            it.copy(password = newText)
        }
    }

    fun login() {
        val user = uiState.value
        System.out.println(user)
        if (user.name == "" || user.e_mail == "" || user.password == "") {
            // TODO: тут надо вывести сообщение об ошибке, диалог и все такое
            // TODO: Проверка корректного ввода всех полей
        } else {
            authRepository.login(user.name, user.e_mail, user.password)


        }
    }

    fun getUser(): UserLogin {
        return authRepository.getUser()
    }


}
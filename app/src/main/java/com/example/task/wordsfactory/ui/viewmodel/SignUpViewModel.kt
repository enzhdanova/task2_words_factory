package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.model.UserLogin
import com.example.task.wordsfactory.ui.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
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
        System.out.println(user)
        if (user?.name == "" || user?.email == "" || user?.password == "") {
            // TODO: тут надо вывести сообщение об ошибке, диалог и все такое
            // TODO: Проверка корректного ввода всех полей
        } else {
            user?.let { authRepository.login(it.name, it.email, it.password) }
        }
    }

    fun getUser(): UserLogin {
        return authRepository.getUser()
    }
}
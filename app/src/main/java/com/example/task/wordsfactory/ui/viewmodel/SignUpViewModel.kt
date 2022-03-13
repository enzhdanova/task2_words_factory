package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun editTextNameChanged(newText: String){
        _uiState.update {
            it.copy(name = newText)
        }
    }

    fun editTextEmailChanged(newText: String){
        _uiState.update {
            it.copy(e_mail = newText)
        }
    }

    fun editTextPasswordChanged(newText: String){
        _uiState.update {
            it.copy(password = newText)
        }
    }



}
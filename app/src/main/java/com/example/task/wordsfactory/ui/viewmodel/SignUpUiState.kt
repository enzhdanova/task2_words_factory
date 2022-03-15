package com.example.task.wordsfactory.ui.viewmodel

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val error: Boolean = false,
    val errorMessage: String = "",
    val successLogin: Boolean = false
)
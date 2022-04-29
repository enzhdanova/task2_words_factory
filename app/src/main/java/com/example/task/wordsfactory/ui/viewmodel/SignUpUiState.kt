package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val error: Boolean = false,
    @StringRes val errorMessage: Int = R.string.other_error,
    val successLogin: Boolean = false
)
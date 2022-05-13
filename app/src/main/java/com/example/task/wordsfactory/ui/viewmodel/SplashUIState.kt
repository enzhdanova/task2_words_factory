package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

data class SplashUIState(
    val error: Boolean = false,
    @StringRes val errorMessage: Int = R.string.other_error,
    val successLogin: Boolean = false
)

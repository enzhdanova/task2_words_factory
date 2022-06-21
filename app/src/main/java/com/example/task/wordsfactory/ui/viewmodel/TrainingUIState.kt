package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

data class TrainingUIState(
    val countWord: Long = 0,
    val error: Boolean = false,
    @StringRes val errorMessage: Int = R.string.other_error,
)
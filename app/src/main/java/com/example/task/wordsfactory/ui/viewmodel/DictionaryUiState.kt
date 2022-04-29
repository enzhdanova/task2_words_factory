package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.model.Word

data class DictionaryUiState(
    val word: Word? = null,
    @StringRes val errorMessage: Int = R.string.other_error,
    val error: Boolean = false
)

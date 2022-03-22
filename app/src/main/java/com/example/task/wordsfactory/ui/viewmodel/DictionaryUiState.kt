package com.example.task.wordsfactory.ui.viewmodel

import com.example.task.wordsfactory.ui.entity.Word

data class DictionaryUiState(
    val word: Word? = null,
    val errorMessage: String = "",
    val error: Boolean = false
)

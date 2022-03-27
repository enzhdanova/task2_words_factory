package com.example.task.wordsfactory.ui.viewmodel

import com.example.task.wordsfactory.ui.entity.WordUI

data class DictionaryUiState(
    val word: WordUI? = null,
    val errorMessage: String = "",
    val error: Boolean = false
)

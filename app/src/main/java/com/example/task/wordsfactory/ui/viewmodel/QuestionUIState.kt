package com.example.task.wordsfactory.ui.viewmodel

import com.example.task.wordsfactory.data.model.Word

data class QuestionUIState (
    val wordsForTraining: List<Word> = listOf(),
    val indexNowWord: Int = 0,
)
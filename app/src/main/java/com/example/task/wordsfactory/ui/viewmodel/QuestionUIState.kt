package com.example.task.wordsfactory.ui.viewmodel

data class QuestionUIState(
    val countQuestions: Int = 0,
    val numberNowQuestion: Int = 0,
    val nowQuestion: String = "",
    val answer: List<String> = listOf(),
    val rightAnswer: String = "",
    val countRightAnswer: Int = 0,
    val setAnswer: Boolean = false
)
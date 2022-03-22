package com.example.task.wordsfactory.ui.entity

data class Word(
    val word: String,
    val phonetic: String,
    val meanings: List<Meanings>
)
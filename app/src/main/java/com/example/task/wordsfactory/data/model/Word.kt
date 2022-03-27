package com.example.task.wordsfactory.data.model

data class Word(
    val id: Long,
    val word: String,
    val phonetic: String,
    val partOfSpeech: List<Long>
)
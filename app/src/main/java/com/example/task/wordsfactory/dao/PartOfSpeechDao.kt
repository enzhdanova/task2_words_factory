package com.example.task.wordsfactory.dao

data class PartOfSpeechDao(
    val id: Long,
    val partOfSpeech: String,
    val meaning: List<Long>
)
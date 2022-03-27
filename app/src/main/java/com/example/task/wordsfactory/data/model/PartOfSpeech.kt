package com.example.task.wordsfactory.data.model

data class PartOfSpeech(
    val id: Long,
    val partOfSpeech: String,
    val meaning: List<Long>
)
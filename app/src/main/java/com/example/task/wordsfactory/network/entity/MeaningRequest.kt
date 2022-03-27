package com.example.task.wordsfactory.network.entity

data class MeaningRequest (
    val partOfSpeech: String,
    val definitions: List<DefinitionRequest>
)
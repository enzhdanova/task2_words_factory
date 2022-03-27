package com.example.task.wordsfactory.network.entity

data class DefinitionRequest(
    val definition: String,
    val synonyms: String,
    val antonyms: String,
    val example: String
)
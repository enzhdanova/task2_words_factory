package com.example.task.wordsfactory.network.entity

data class WordRequest (
    val word: String,
    val phonetic: String,
    val phonetics: List<PhoneticRequest>,
    val meanings: List<MeaningRequest>,
    val license: License,
    val sourceUrls: List<String>
)

data class License(val name: String, val url: String)
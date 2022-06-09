package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.model.Word

interface DictionaryRepository {
    suspend fun getWord(searchWord: String): Result<Word>
    suspend fun addToDictionary(word: Word): Result<Boolean>
    suspend fun getCountWords(): Result<Long>
    suspend fun updateWord(word: Word): Result<Boolean>
    suspend fun getTrainingWord(): Result<List<Word>>
}
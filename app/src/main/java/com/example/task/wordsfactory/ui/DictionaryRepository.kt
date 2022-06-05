package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.model.Word

interface DictionaryRepository {
    suspend fun getWord(searchWord: String): Result<Word>
    suspend fun addToDictionary(word: Word): Result<Boolean>
    suspend fun getCountWords(): Long
    suspend fun increaseCoefficient(word: Word)
    suspend fun decreaseCoefficient(word: Word)

    //TODO: Возможно надо убрать, пока необходимо для тестирования
    suspend fun studyCoefficient(word: Word): Long

    //TODO: Возможно надо убрать, пока необходимо для тестирования
    suspend fun getAllWord(): List<Word>

    suspend fun getTrainingWord(): List<Word>
}
package com.example.task.wordsfactory.ui

import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.data.model.Word

interface DictionaryRepository {
    suspend fun getWord(searchWord: String): Result<Word>
}
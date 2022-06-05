package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.datasource.LocalDataSource
import com.example.task.wordsfactory.data.datasource.RemoteDataSource
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DictionaryRepository {

    override suspend fun getWord(searchWord: String): Result<Word> {
        val result = remoteDataSource.getWord(searchWord)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }

    override suspend fun addToDictionary(word: Word): Result<Boolean> {
        return localDataSource.addWord(word)
    }

    override suspend fun getCountWords(): Long = localDataSource.getCountWords()
}
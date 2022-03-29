package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.data.model.Word

class DictionaryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {


    suspend fun getWord(searchWord: String): Result<Word> {
        val result = remoteDataSource.getWord(searchWord)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }
}
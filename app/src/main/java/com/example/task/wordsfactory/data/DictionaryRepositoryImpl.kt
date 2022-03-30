package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DictionaryRepository {

    override suspend fun getWord(searchWord: String): Result<Word> {
        val result = remoteDataSource.getWord(searchWord)
        println("________remoteDataSource.getWord(searchWord)______________")
        println(result)
        result.onSuccess {
            println(it.meanings.size)
        }
        println()
        println("________DictionaryRepository______________")
        val tmp = localDataSource.getWord(searchWord)
        println(tmp)
        tmp.onSuccess {
            println(it.meanings.size)

        }

        println("________DictionaryRepository______________")
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }
}
package com.example.task.wordsfactory.data.dataSource

import com.example.task.wordsfactory.data.model.Word

class RemoteDataSource {
    fun getWord(searchWord: String): Result<Word> = Result.failure(Exception("Что-то пошло не так"))
}
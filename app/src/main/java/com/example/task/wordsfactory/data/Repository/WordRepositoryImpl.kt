package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.MockeWordModel
import com.example.task.wordsfactory.data.model.Word

class WordRepositoryImpl {

    fun getWord(searchWord: String): Result<Word> {
        val res  = findWord(searchWord)
        return if (res != null) {
            Result.success(res)
        } else {
            Result.failure(Exception("Слово не найдено"))
        }
    }

    private fun findWord(searchWord: String): Word? {
        val res: Word? = MockeWordModel.words.find {
            it.word == searchWord
        }
        return res
    }

}
package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.Word


class DictionaryRepositoryImpl {
    fun getWord(searchWord: String): Result<Word> {
        val res  = findWord(searchWord)
        return if (res != null) {
            Result.success(res)
        } else {
            Result.failure(Exception("Слово не найдено"))
        }
    }

    private fun findWord(searchWord: String): Word? {
        val res: Word? = MockeWord.words.find {
            it.word == searchWord
        }
        return res
    }

}
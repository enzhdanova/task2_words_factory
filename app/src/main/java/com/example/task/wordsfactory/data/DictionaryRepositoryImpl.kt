package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.Word


class DictionaryRepositoryImpl {
    fun getWord(searchWord: String): Result<Word> {
        return if (MockeWord.cookingWord.word == searchWord) {
            Result.success(MockeWord.cookingWord)
        } else {
            Result.failure(Exception("Слово не найдено"))
        }
    }

}
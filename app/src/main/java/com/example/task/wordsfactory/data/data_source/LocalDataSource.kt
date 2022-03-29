package com.example.task.wordsfactory.data.data_source

import com.example.task.wordsfactory.data.MockeWord
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word

class LocalDataSource {

    private val partOfSpeech = MockeWord.cookingWord.partOfSpeech

    private val meaning = MockeWord.cookingWord.meanings

    fun getWord(searchWord: String): Result<Word> {
        val res = findWord(searchWord)
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

    fun getPartOfSpeech(id: Long): Result<String> =
        Result.success(partOfSpeech)

}
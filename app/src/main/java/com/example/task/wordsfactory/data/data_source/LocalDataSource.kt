package com.example.task.wordsfactory.data.data_source

import com.example.task.wordsfactory.data.MockeWord
import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI

class LocalDataSource {

    private val partOfSpeech = MockeWord.cookingWord.partOfSpeech

    private val meaning = MockeWord.cookingWord.meanings

    fun getWord(searchWord: String): Result<WordUI> {
        val res = findWord(searchWord)
        return if (res != null) {
            Result.success(res)
        } else {
            Result.failure(Exception("Слово не найдено"))
        }
    }

    private fun findWord(searchWord: String): WordUI? {
        val res: WordUI? = MockeWord.words.find {
            it.word == searchWord
        }
        return res
    }

    fun getPartOfSpeech(id: Long): Result<String> =
        Result.success(partOfSpeech)

    fun getMeaning(id: Long): Result<MeaningUI> =
        Result.success(meaning.first {
            it.id == id
        }
        )

}
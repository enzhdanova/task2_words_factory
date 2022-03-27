package com.example.task.wordsfactory.domain

import com.example.task.wordsfactory.data.Repository.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI

class GetWordUseCase(
    private val dictionaryRepositoryImpl: DictionaryRepositoryImpl
) {

    fun getWord(searchWord: String): Result<WordUI> {
        val result = dictionaryRepositoryImpl.getWord(searchWord)

        if (result.isFailure) return Result.failure(Exception("Слово не найдено"))

        result.onSuccess { resWord ->
            return Result.success(getWordEntity(resWord))
        }

        return Result.failure(Exception("Слово не найдено"))
    }

    private fun getWordEntity(resWord: Word): WordUI {
        val partOfSpeechList = dictionaryRepositoryImpl.partOfSpeechList
        val meaningList = dictionaryRepositoryImpl.meaningList


        val partOfSpeechStr = partOfSpeechList.joinToString {
            it.partOfSpeech
        }

        val meanings = mutableListOf<MeaningUI>()

        meaningList.forEach {
            meaning -> meanings.add(meaningModelToUIEntity(meaning))
        }

        return wordModelToUIEntity(resWord, partOfSpeechStr, meanings)
    }

    private fun meaningModelToUIEntity(meaning: Meaning): MeaningUI {
        return MeaningUI(
            id = meaning.id,
            definition = meaning.definition,
            example = meaning.example
        )
    }

    private fun wordModelToUIEntity(
        word: Word,
        partOfSpeech: String,
        meanings: List<MeaningUI>
    ): WordUI {
        return WordUI(
            id = word.id,
            word = word.word,
            phonetic = word.phonetic,
            partOfSpeech = partOfSpeech,
            meanings = meanings
        )
    }


}
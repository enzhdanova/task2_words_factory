package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI

class DictionaryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getPartOfSpeech(id: Long): Result<String> {
        val result = remoteDataSource.getPartOfSpeech(id)

        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getPartOfSpeech(id)
        }
    }

    fun getWord(searchWord: String): Result<WordUI> {
        val result = remoteDataSource.getWord(searchWord)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }

    fun getMeaning(id: Long): Result<MeaningUI> {
        val result = remoteDataSource.getMeaning(id)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getMeaning(id)
        }
    }

    fun WordToUIEntity(
        word: Word,
        partOfSpeech: List<PartOfSpeech>,
        meanings: List<Meaning>
    ): WordUI {

        val meaningsUI = meaningToEntity(meanings)
        val partOfSpeechStr = partOfSpeech.joinToString {
            it.partOfSpeech
        }

        return WordUI(
            id = word.id,
            word = word.word,
            phonetic = word.phonetic,
            partOfSpeech = partOfSpeechStr,
            meanings = meaningsUI
        )
    }

    fun meaningToEntity(meanings: List<Meaning>): List<MeaningUI> {
        val meaningsUI = mutableListOf<MeaningUI>()
        meanings.forEach {
            meaningsUI.add(
                MeaningUI(
                    id = it.id,
                    definition = it.definition,
                    example = it.example
                )
            )
        }
        return meaningsUI
    }

}
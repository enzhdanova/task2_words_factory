package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.MockeWordModel
import com.example.task.wordsfactory.data.dataSource.RemoteDataSource
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.network.entity.DefinitionRequest

class DictionaryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
) {

//-------------------------------------------------------------------------
    private val partOfSpeech = MockeWordModel.partOfSpeech

    fun getPartOfSpeech(id: Long): PartOfSpeech =
        partOfSpeech.first{
            it.id == id
        }

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

    private val meaning = MockeWordModel.meanings

    fun getMeaning(id: Long): Meaning =
        meaning.first{
            it.id == id
        }

}
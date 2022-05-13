package com.example.task.wordsfactory.data.datasource

import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.database.dao.DictionaryDao
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dictionaryDao: DictionaryDao
) {
    suspend fun getWord(searchWord: String): Result<Word> {
        return withContext(Dispatchers.IO) {
            try {
                val res = dictionaryDao.getWord(searchWord)
                val meaning = res.id?.let { idWord ->
                    dictionaryDao.getMeaning(idWord).map { meaning ->
                        meaning.toModel()
                    }
                } ?: emptyList()
                val wordModel = res.toModelWithMeaning(meaning = meaning)
                Result.success(wordModel)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun addWord(word: Word): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val wordId = dictionaryDao.insertWord(
                    WordBD(
                        word = word.word,
                        phonetic = word.phonetic,
                        partOfSpeech = word.partOfSpeech
                    )
                )
                val meaningsDB = word.meanings.map {
                    MeaningBD(
                        definition = it.definition,
                        example = it.example,
                        word_id = wordId
                    )
                }
                dictionaryDao.insertMeanings(meaningsDB)
                Result.success(true)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }
}

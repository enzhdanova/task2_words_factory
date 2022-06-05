package com.example.task.wordsfactory.data.datasource

import com.example.task.wordsfactory.data.model.Meaning
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
                    wordToWordDB(word, 0)
                )
                val meaningsDB = word.meanings.map {
                    meaningToMeaningDB(meaning = it, wordId = wordId)
                }
                dictionaryDao.insertMeanings(meaningsDB)
                Result.success(true)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun getCountWords(): Long =
        withContext(Dispatchers.IO) {
            dictionaryDao.getCountWords()
        }

    suspend fun increaseCoefficient(word: Word) {
        withContext(Dispatchers.IO) {
            val newCoefficient = dictionaryDao.studyCoefficient(word.word) + 1
            val wordBD = wordToWordDB(word, studyCoefficient = newCoefficient)
            dictionaryDao.updateWord(wordBD)
        }
    }

    suspend fun decreaseCoefficient(word: Word) {
        withContext(Dispatchers.IO) {
            val newCoefficient = dictionaryDao.studyCoefficient(word.word) - 1
            val wordBD = wordToWordDB(word, studyCoefficient = newCoefficient)
            dictionaryDao.updateWord(wordBD)
        }
    }

    //TODO: Возможно надо убрать, пока необходимо для тестирования
    suspend fun getStudyCoefficient(word: Word): Long = withContext(Dispatchers.IO) {
        dictionaryDao.studyCoefficient(word.word)
    }

    private fun wordToWordDB(word: Word, studyCoefficient: Long) = WordBD(
        id = word.id,
        word = word.word,
        phonetic = word.phonetic,
        partOfSpeech = word.partOfSpeech,
        studyCoefficient = studyCoefficient
    )

    private fun meaningToMeaningDB(meaning: Meaning, wordId: Long) = MeaningBD(
        definition = meaning.definition,
        example = meaning.example,
        word_id = wordId
    )


    //TODO: Возможно надо убрать, пока необходимо для тестирования
    suspend fun getAllWords() = withContext(Dispatchers.IO) {
        dictionaryDao.getAllWord()
    }

    suspend fun getTrainingWord() = withContext(Dispatchers.IO) {
        dictionaryDao.getTrainingWords()
    }
}
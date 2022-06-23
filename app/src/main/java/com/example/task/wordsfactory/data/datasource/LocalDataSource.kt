package com.example.task.wordsfactory.data.datasource

import com.example.task.wordsfactory.data.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.WordsForPlugAnswer
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
    companion object {
        private const val COUNT_ANSWER_IN_QUESTION = 3
    }

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
                    WordBD.fromDomain(word)
                )
                val meaningsDB = word.meanings.map {
                    MeaningBD.fromDomain(meaning = it, wordId = wordId)
                }
                dictionaryDao.insertMeanings(meaningsDB)
                Result.success(true)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun getCountWords(): Result<Long> {
        return withContext(Dispatchers.IO) {
            try {
                val count = dictionaryDao.getCountWords()
                Result.success(count)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun updateWord(word: Word): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            try {
                val wordBD = WordBD.fromDomain(word)
                dictionaryDao.updateWord(wordBD)
                Result.success(true)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun getTrainingWord(): Result<List<Word>> {
        return withContext(Dispatchers.IO) {
            try {
                val listWords = dictionaryDao.getTrainingWords()
                val resultList = listWords.map { wordBD ->
                    wordBD.toModel()
                }
                Result.success(resultList)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun getWordsForQuestion(rightWord: String): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val otherWords = dictionaryDao.getWrongWordsForQuestion(rightWord).toMutableList()
                otherWords.add(rightWord)

                if (otherWords.size < COUNT_ANSWER_IN_QUESTION) {
                    otherWords.addAll(
                        getPlugAnswers(COUNT_ANSWER_IN_QUESTION - otherWords.size, otherWords)
                    )
                }

                Result.success(otherWords)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    suspend fun getRandomMeaning(word_id: Long): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val meaning = dictionaryDao.getRandomMeaning(word_id)
                Result.success(meaning)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

    private fun getPlugAnswers(countAnswer: Int, words: List<String>): List<String> =
        WordsForPlugAnswer.plugAnswers.filterNot { plug ->
            words.contains(plug)
        }.shuffled().take(countAnswer)


}
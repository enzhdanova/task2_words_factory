package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.datasource.LocalDataSource
import com.example.task.wordsfactory.data.datasource.RemoteDataSource
import com.example.task.wordsfactory.data.model.Question
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.DictionaryRepository
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DictionaryRepository {

    override suspend fun getWord(searchWord: String): Result<Word> {
        val result = remoteDataSource.getWord(searchWord)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }

    override suspend fun addToDictionary(word: Word): Result<Boolean> {
        return localDataSource.addWord(word)
    }

    override suspend fun getCountWords(): Result<Long> {
        val result = localDataSource.getCountWords()
        return if (result.isSuccess) {
            result
        } else {
            Result.failure(Exception())
        }
    }

    override suspend fun updateWord(word: Word): Result<Boolean> =
        localDataSource.updateWord(word)

    override suspend fun getTrainingWord(): Result<List<Word>> =
        localDataSource.getTrainingWord()

    override suspend fun getQuestion(rightWord: Word): Result<Question> {
        val meaning = localDataSource
            .getRandomMeaning(rightWord.id)
            .getOrNull()
        val answers = localDataSource
            .getWordsForQuestion(rightWord.word)
            .getOrNull()

        return if (answers == null || meaning == null) {
            Result.failure(NullPointerException())
        } else {
            Result.success(Question(nowQuestion = meaning, answers = answers))
        }
    }


}
package com.example.task.wordsfactory.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD

@Dao
interface DictionaryDao {
    @Insert
    fun insertWord(word: WordBD): Long

    @Insert
    fun insertMeanings(meaning: List<MeaningBD>)

    @Query("SELECT * FROM WordBD WHERE word = :searchWord")
    fun getWord(searchWord: String): WordBD

    @Query("SELECT id FROM WordBD WHERE word = :searchWord")
    fun getWordId(searchWord: String): Long

    @Query("SELECT * FROM MeaningBD WHERE word_id = :word_id")
    fun getMeaning(word_id: Long): List<MeaningBD>

    @Query("SELECT COUNT(*) FROM WordBD")
    fun getCountWords(): Long

    @Update
    fun updateWord(word: WordBD): Int

    @Query("SELECT studyCoefficient FROM WordBD WHERE word = :wordStr")
    fun studyCoefficient(wordStr: String): Long

    @Query("SELECT * FROM WordBD")
    fun getAllWord(): List<WordBD>

    @Query("SELECT * FROM WordBD " +
            "WHERE studyCoefficient = (SELECT MIN(studyCoefficient) FROM WordBD)" +
            "LIMIT 5")
    fun getTrainingWords(): List<WordBD>

    @Query("SELECT word FROM WordBD " +
            "WHERE word != :rightWord ORDER BY RANDOM() " +
            "LIMIT 2")
    fun getWrongWordsForQuestion(rightWord: String): List<String>

    @Query("SELECT definition FROM MeaningBD WHERE word_id = :word_id " +
            "ORDER BY RANDOM()" +
            "LIMIT 2")
    fun getRandomMeaning(word_id: Long): String
}
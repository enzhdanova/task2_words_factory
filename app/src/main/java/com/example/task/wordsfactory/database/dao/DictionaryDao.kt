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
}
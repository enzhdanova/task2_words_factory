package com.example.task.wordsfactory.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD

@Dao
interface DictionaryDao {
    @Insert
    fun insertWord(word: WordBD): Long

    @Insert
    fun insertMeanings(meaning: List<MeaningBD>)

    @Query ("SELECT * FROM WordBD WHERE word = :searchWord")
    fun getWord(searchWord: String): WordBD

    @Query ("SELECT id FROM WordBD WHERE word = :searchWord")
    fun getWordId(searchWord: String): Long

    @Query ("SELECT * FROM MeaningBD WHERE word_id = :word_id")
    fun getMeaning(word_id: Long): List<MeaningBD>
}
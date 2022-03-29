package com.example.task.wordsfactory.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.task.wordsfactory.database.entity.WordBD

@Dao
interface DictionaryDao {
    @Insert
    fun insertWord(vararg  word: WordBD)

    @Query ("SELECT * FROM WordBD WHERE word = :searchWord")
    fun getWord(searchWord: String): WordBD

}
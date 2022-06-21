package com.example.task.wordsfactory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task.wordsfactory.database.dao.DictionaryDao
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD

@Database(entities = [WordBD::class, MeaningBD::class], version = 8)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}
package com.example.task.wordsfactory.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pos_table")
data class PartOfSpeechBD(
    @PrimaryKey val id: Long,
    val partOfSpeech: String,
    val word_id: Long
)
package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class WordBD(
    @PrimaryKey val id: Long,
    val word: String,
    val phonetic: String
)
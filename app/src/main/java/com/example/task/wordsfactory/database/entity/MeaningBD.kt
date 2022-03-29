package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meaning_table")
data class MeaningBD(
    @PrimaryKey val id: Long,
    val definition: String,
    val example: String,
    val pofs_id: Long
)

package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.task.wordsfactory.data.model.Meaning

@Entity(indices = [Index(value = ["definition", "example"],
    unique = true)])
data class MeaningBD(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val definition: String,
    val example: String,
    val word_id: Long
) {
    fun toModel() = Meaning(
        definition = definition,
        example = example
    )
}

package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.task.wordsfactory.data.model.Meaning

@Entity
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

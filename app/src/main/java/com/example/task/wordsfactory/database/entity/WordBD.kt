package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word


@Entity
data class WordBD(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val word: String,
    val phonetic: String,
    val partOfSpeech: String
) {
    fun toModel() = Word(
        word = word,
        phonetic = phonetic,
        partOfSpeech = partOfSpeech,
        meanings = emptyList()
    )

    fun toModelWithMeaning(meaning: List<Meaning>) = Word(
        word = word,
        phonetic = phonetic,
        partOfSpeech = partOfSpeech,
        meanings = meaning
    )
}
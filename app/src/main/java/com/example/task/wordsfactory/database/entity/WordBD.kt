package com.example.task.wordsfactory.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word


@Entity(
    indices = [Index(
        value = ["word"],
        unique = true
    )]
)
data class WordBD(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val word: String,
    val phonetic: String?,
    val partOfSpeech: String,
    val studyCoefficient: Long
) {
    fun toModel() = Word(
        id = id ?: 0,
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

    companion object {
        fun toWordDB(word: Word) = WordBD(
            id = word.id,
            word = word.word,
            phonetic = word.phonetic,
            partOfSpeech = word.partOfSpeech,
            studyCoefficient = word.studyCoefficient
        )
    }
}
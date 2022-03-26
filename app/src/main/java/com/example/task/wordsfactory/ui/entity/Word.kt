package com.example.task.wordsfactory.ui.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val id: Long,
    val word: String,
    val phonetic: String,
    val partOfSpeech: String,
    val meanings: List<Meaning>
) : Parcelable
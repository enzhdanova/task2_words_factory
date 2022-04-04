package com.example.task.wordsfactory.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val word: String,
    val phonetic: String,
    val partOfSpeech: String,
    val meanings: List<Meaning>,
    val audio: String? = null
) : Parcelable
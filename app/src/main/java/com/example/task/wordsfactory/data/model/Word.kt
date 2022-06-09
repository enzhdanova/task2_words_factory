package com.example.task.wordsfactory.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val id: Long = 0,
    val word: String,
    val phonetic: String?,
    val partOfSpeech: String,
    val meanings: List<Meaning>,
    val audio: String? = null,
    val studyCoefficient: Long = 0
) : Parcelable
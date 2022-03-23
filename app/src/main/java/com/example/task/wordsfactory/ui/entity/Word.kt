package com.example.task.wordsfactory.ui.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val word: String="",
    val phonetic: String="",
    val meanings: List<Meanings>?=null
) : Parcelable
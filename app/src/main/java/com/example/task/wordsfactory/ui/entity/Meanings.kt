package com.example.task.wordsfactory.ui.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meanings(val partOfSpeech: String, val definitions: List<Definitions>) : Parcelable

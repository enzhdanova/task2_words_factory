package com.example.task.wordsfactory.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Meaning(val definition: String, val example: String) : Parcelable

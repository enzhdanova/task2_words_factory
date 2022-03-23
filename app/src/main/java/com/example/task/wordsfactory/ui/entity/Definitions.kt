package com.example.task.wordsfactory.ui.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Definitions(val definition: String, val example: String) : Parcelable
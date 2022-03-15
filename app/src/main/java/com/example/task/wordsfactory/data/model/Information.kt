package com.example.task.wordsfactory.data.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Information(
    val title: String,
    val subtitle: String,
    val image: Drawable?
)

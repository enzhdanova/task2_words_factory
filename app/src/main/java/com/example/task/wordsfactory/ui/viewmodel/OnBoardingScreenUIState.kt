package com.example.task.wordsfactory.ui.viewmodel

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task.wordsfactory.R
import java.lang.Error

data class OnBoardingScreenUIState(
    val title: String = "",
    val subtitle: String = "",
    val image: Drawable? = null,
    val error: Boolean = false,
    val errorMessage: String = ""
)


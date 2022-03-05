package com.example.task.wordsfactory.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Information(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val image: Int
)

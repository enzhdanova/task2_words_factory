package com.example.task.wordsfactory.data.mock_data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class InformationRes(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val image: Int
)

package com.example.task.wordsfactory.ui.viewmodal

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

data class OnBoardingScreenState(
    @StringRes val title: Int = R.string.learn,
    @StringRes val subtitle: Int = R.string.subtitle,
    @DrawableRes val image: Int = R.drawable.ic_cool_kids_long_distance_relationship
)
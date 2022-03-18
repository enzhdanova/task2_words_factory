package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task.wordsfactory.data.OnboardingInfoEnum

data class OnBoardingScreenUIState(
    @StringRes val title: Int = OnboardingInfoEnum.One.title,
    @StringRes val subtitle: Int = OnboardingInfoEnum.One.title,
    @DrawableRes val image: Int = OnboardingInfoEnum.One.image,
    val error: Boolean = false,
    val errorMessage: String = ""
)


package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task.wordsfactory.data.OnboardingStep

data class OnboardingFragmentUIState(
    @StringRes
    val title: Int = OnboardingStep.One.title,
    @StringRes
    val subtitle: Int = OnboardingStep.One.title,
    @DrawableRes
    val image: Int = OnboardingStep.One.image
)
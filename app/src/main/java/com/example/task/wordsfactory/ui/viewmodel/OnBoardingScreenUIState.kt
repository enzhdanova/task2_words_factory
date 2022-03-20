package com.example.task.wordsfactory.ui.viewmodel

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

data class OnBoardingScreenUIState(
    val fragmentUiStateList: List<OnboardingFragmentUIState>,
    val error: Boolean = false,
    val errorMessage: String = "",
    @StringRes val buttonText: Int = R.string.next
)


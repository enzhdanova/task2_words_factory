package com.example.task.wordsfactory

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

enum class TimerColors(@DrawableRes val progressBarColor: Int,
                       @ColorRes val textColor: Int) {
    PROGRESS5(
        progressBarColor = R.drawable.custom_circular_progress_bar5,
        textColor = R.color.progress5
    ),
    PROGRESS4(
        progressBarColor = R.drawable.custom_circular_progress_bar4,
        textColor = R.color.progress4
    ),
    PROGRESS3(
        progressBarColor = R.drawable.custom_circular_progress_bar3,
        textColor = R.color.progress3
    ),
    PROGRESS2(
        progressBarColor = R.drawable.custom_circular_progress_bar2,
        textColor = R.color.progress2
    ),
    PROGRESS1(
        progressBarColor = R.drawable.custom_circular_progress_bar,
        textColor = R.color.progress1
    ),
    PROGRESS_GO(
        progressBarColor = R.drawable.custom_circular_progress_bar,
        textColor = R.color.progress5
    ),
}
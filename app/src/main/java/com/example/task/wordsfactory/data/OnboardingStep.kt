package com.example.task.wordsfactory.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.task.wordsfactory.R

enum class OnboardingStep(
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @DrawableRes val image: Int,
    @StringRes val buttonText: Int
) {
    One(
        title = R.string.learn,
        subtitle = R.string.subtitle,
        image = R.drawable.ic_cool_kids_long_distance_relationship,
        buttonText = R.string.next
    ),
    Two(
        title = R.string.find,
        subtitle = R.string.subtitle,
        image = R.drawable.ic_cool_kids_staying_home,
        buttonText = R.string.next
    ),
    Three(
        title = R.string.improve,
        subtitle = R.string.subtitle,
        image = R.drawable.ic_cool_kids_high_tech,
        buttonText = R.string.start
    )
}

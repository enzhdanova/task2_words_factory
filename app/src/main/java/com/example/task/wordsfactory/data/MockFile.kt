package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.model.Information

object MockFile {
    val listOfInformation = listOf(
        Information(
            title = R.string.learn,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_long_distance_relationship
        ),
        Information(
            title = R.string.find,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_staying_home
        ),
        Information(
            title = R.string.improve,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_high_tech
        )
    )
}

object MockFile2 {
    val listOfInformation = listOf(
        Information(
            title = R.string.learn2,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_long_distance_relationship
        ),
        Information(
            title = R.string.find2,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_staying_home
        ),
        Information(
            title = R.string.improve2,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_high_tech
        )
    )
}
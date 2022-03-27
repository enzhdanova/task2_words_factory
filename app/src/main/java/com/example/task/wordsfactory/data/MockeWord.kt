package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI

object MockeWord {
    val cookingWord = WordUI(
        id = 0,
        word = "cooking",
        phonetic = "/ˈkʊ.kɪŋ/",
        partOfSpeech = "verb",
        meanings = listOf(
            MeaningUI(
                id = 0,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            MeaningUI(
                id = 1,
                definition = "In progress, happening.",
                example = ""
            ),
            MeaningUI(
                id = 2,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            MeaningUI(
                id = 3,
                definition = "In progress, happening.",
                example = ""
            ),
            MeaningUI(
                id = 4,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            MeaningUI(
                id = 5,
                definition = "In progress, happening.",
                example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
            )
        )
    )

    val cookingWord2 = WordUI(
        id = 0,
        word = "cooking2",
        phonetic = "/ˈkʊ.kɪŋ/222",
        partOfSpeech = "verb2",
        meanings = listOf(
            MeaningUI(
                id = 0,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            )
        )
    )


    val words = listOf(
        cookingWord, cookingWord2
    )
}
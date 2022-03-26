package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.Meaning
import com.example.task.wordsfactory.ui.entity.Word

object MockeWord {
    val cookingWord = Word(
        id = 0,
        word = "cooking",
        phonetic = "/ˈkʊ.kɪŋ/",
        partOfSpeech = "verb",
        meanings = listOf(
            Meaning(
                id = 0,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                id = 1,
                definition = "In progress, happening.",
                example = ""
            ),
            Meaning(
                id = 2,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                id = 3,
                definition = "In progress, happening.",
                example = ""
            ),
            Meaning(
                id = 4,
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                id = 5,
                definition = "In progress, happening.",
                example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
            )
        )
    )

    val cookingWord2 = Word(
        id = 0,
        word = "cooking2",
        phonetic = "/ˈkʊ.kɪŋ/222",
        partOfSpeech = "verb2",
        meanings = listOf(
            Meaning(
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
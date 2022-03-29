package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word

object MockeWord {
    val cookingWord = Word(
        word = "cooking",
        phonetic = "/ˈkʊ.kɪŋ/",
        partOfSpeech = "verb",
        meanings = listOf(
            Meaning(
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                definition = "In progress, happening.",
                example = ""
            ),
            Meaning(
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                definition = "In progress, happening.",
                example = ""
            ),
            Meaning(
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            ),
            Meaning(
                definition = "In progress, happening.",
                example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
            )
        )
    )

    val cookingWord2 = Word(
        word = "cooking2",
        phonetic = "/ˈkʊ.kɪŋ/222",
        partOfSpeech = "verb2",
        meanings = listOf(
            Meaning(
                definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "I'm cooking bangers and mash."
            )
        )
    )


    val words = listOf(
        cookingWord, cookingWord2
    )
}
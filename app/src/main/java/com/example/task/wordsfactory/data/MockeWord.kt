package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.Meaning
import com.example.task.wordsfactory.ui.entity.Word

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
                example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
            )
        )
    )

    val cookingWord2 = Word(
        word = "cooking2",
        phonetic = "/ˈkʊ.kɪŋ/2",
        partOfSpeech = "verb1",
        meanings = listOf(
            Meaning(
                definition = "11111To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                example = "1111I'm cooking bangers and mash."
            ),
            Meaning(
                definition = "In p1111rogress, happening.",
                example = "The project11111 took a few da"
            )
        )
    )


    val words = listOf(
        cookingWord, cookingWord2
    )
}
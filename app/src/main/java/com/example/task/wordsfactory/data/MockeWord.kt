package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.ui.entity.Definitions
import com.example.task.wordsfactory.ui.entity.Meanings
import com.example.task.wordsfactory.ui.entity.Word

object MockeWord {
    val cookingWord = Word(
        word = "cooking",
        phonetic = "/ˈkʊ.kɪŋ/",
        meanings = listOf(
            Meanings(
                partOfSpeech = "verb",
                definitions = listOf(
                    Definitions(
                        definition = "To prepare (food) for eating by heating it, often by combining it with other ingredients.",
                        example = "I'm cooking bangers and mash."
                    )
                )
            ),
            Meanings(
                partOfSpeech = "adjective",
                definitions = listOf(
                    Definitions(
                        definition = "In progress, happening.",
                        example = "The project took a few days to gain momentum, but by the end of the week, things were really cooking."
                    )
                )
            )
        )
    )
}
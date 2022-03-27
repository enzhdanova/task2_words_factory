package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word

object MockeWordModel {
    val cookingWord = Word(
        id = 0,
        word = "cooking",
        phonetic = "/ˈkʊ.kɪŋ/",
        partOfSpeech = listOf(0,1)
    )

    val cookingWord2 = Word(
        id = 1,
        word = "cooking2",
        phonetic = "/ˈkʊ.kɪŋ/222",
        partOfSpeech = listOf(2)
    )

    val meanings = listOf(
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

    val partOfSpeech = listOf(
        PartOfSpeech(0, "verb", listOf(0,3)),
        PartOfSpeech(1, "verb2", listOf(1)),
        PartOfSpeech(2, "verb3", listOf(5))
    )

    val words = listOf(
        cookingWord, cookingWord2
    )


}
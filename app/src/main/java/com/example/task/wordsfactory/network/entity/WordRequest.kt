package com.example.task.wordsfactory.network.entity

import com.example.task.wordsfactory.data.model.Word

data class WordRequest(
    val word: String,
    val phonetic: String?,
    val meanings: List<MeaningRequest>,
    val phonetics: List<PhoneticsRequest>
) {
    fun toModelWithMeanings(): Word = Word(
        word = word,
        phonetic = phonetic,
        partOfSpeech = getPartOfSpeechToString(),
        meanings = meanings.flatMap { meaning ->
            meaning.toModel()
        },
        audio = phonetics.firstOrNull()?.toModel()
    )

    private fun getPartOfSpeechToString(): String =
        meanings.map {
            it.partOfSpeech
        }.toSet().joinToString()
}

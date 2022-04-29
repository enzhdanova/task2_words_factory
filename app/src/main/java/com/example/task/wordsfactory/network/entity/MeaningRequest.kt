package com.example.task.wordsfactory.network.entity

import com.example.task.wordsfactory.data.model.Meaning

data class MeaningRequest (
    val partOfSpeech: String,
    val definitions: List<DefinitionRequest>
) {
    fun toModel(): List<Meaning> {
        return definitions.map {
                definitions ->
            Meaning(
                definition = definitions.definition,
                example = definitions.example.orEmpty()
            )
        }
    }
}
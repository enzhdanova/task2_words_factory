package com.example.task.wordsfactory.network.entity

data class PhoneticsRequest (val audio: String) {
    fun toModel(): String? {
        return audio.ifEmpty {
            null
        }
    }
}
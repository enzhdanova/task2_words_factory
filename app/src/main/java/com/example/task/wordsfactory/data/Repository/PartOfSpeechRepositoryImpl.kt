package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.MockeWordModel
import com.example.task.wordsfactory.data.model.PartOfSpeech

class PartOfSpeechRepositoryImpl {
    private val partOfSpeech = MockeWordModel.partOfSpeech

    fun getPartOfSpeech(id: Long): PartOfSpeech =
        partOfSpeech.first{
            it.id == id
        }

}
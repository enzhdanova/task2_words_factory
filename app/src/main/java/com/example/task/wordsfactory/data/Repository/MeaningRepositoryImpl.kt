package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.MockeWordModel
import com.example.task.wordsfactory.data.model.Meaning

class MeaningRepositoryImpl {
    private val meaning = MockeWordModel.meanings

    fun getMeaning(id: Long): Meaning =
        meaning.first{
            it.id == id
        }
}
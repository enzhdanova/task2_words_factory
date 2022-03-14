package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.Information

interface InformationRepository {
    fun getInfo(position: Int) : Result<Information>
}
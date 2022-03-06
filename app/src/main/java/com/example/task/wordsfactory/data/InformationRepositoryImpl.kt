package com.example.task.wordsfactory.data

import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor() : InformationRepository {
    private val information = MockFile.listOfInformation
    override fun getInfo(position: Int) = information[position]
}
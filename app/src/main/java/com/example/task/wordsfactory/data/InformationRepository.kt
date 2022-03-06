package com.example.task.wordsfactory.data

import javax.inject.Inject

class InformationRepository @Inject constructor() {
    val information = MockFile2.listOfInformation
}
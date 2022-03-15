package com.example.task.wordsfactory.data

import com.example.task.wordsfactory.data.model.Information
import java.io.IOException
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor() : InformationRepository {

    companion object {
        private const val error_mssg = "Возникла проблема при получении данных"
    }

    private val information = MockFile.listOfInformation
    override fun getInfo(position: Int): Result<Information> {
        return try {
            Result.Success(information[position])
        } catch (ioe: IOException) {
            return Result.Error(Exception(error_mssg))
        }
    }
}
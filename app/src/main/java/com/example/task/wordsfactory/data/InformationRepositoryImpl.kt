package com.example.task.wordsfactory.data

import androidx.annotation.StringRes
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.model.Information
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor() : InformationRepository {
    private val information = MockFile.listOfInformation
    override fun getInfo(position: Int): Result<Information> {
     /*   if (information[position] != null){
            return Result.Success(information[position])
        }*/

        //TODO: тут должна быть строка, а не ресурс
        return Result.Error(Exception(R.string.error_message.toString()))
    }
}
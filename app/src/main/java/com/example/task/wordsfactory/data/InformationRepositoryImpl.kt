package com.example.task.wordsfactory.data

import android.content.Context
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.mock_data.InformationDataSource
import com.example.task.wordsfactory.data.model.Information
import java.io.IOException
import javax.inject.Inject

class InformationRepositoryImpl @Inject constructor(
    private val context: Context,
    private val informationDataSource: InformationDataSource
) : InformationRepository {

    override fun getInfo(position: Int): Result<Information> {
        return try {
            val information = informationDataSource.fetchInformationData(position)
            //Result.Success(information)
            Result.success(information)
        } catch (ioe: IOException) {
            return Result.failure(Exception(context.getString(R.string.error_message))) //.Error(Exception(context.getString(R.string.error_message)))
        }
    }
}
package com.example.task.wordsfactory.data.mock_data

import android.content.Context
import com.example.task.wordsfactory.data.model.Information

class InformationDataSource(private val context: Context) {
    fun fetchInformationData(position: Int): Information {
        val infRes = MockFile.listOfInformation[position]
        return Information(
            title = context.getString(infRes.title),
            subtitle = context.getString(infRes.subtitle),
            image = context.getDrawable(infRes.image)
        )
    }
}
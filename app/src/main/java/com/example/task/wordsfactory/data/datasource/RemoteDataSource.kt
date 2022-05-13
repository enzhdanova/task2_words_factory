package com.example.task.wordsfactory.data.datasource

import com.example.task.wordsfactory.network.WordApi
import com.example.task.wordsfactory.data.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val wordApi: WordApi
) {
    suspend fun getWord(searchWord: String): Result<Word> {
        return withContext(Dispatchers.IO) {
            try {
                val result = wordApi.getWord(searchWord).body()?.first()

                if (result != null) {
                    Result.success(result.toModelWithMeanings())
                } else {
                    Result.failure(NullPointerException())
                }
            } catch (io: IOException) {
                Result.failure(io)
            }
        }
    }
}
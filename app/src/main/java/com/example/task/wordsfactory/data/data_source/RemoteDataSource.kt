package com.example.task.wordsfactory.data.data_source

import com.example.task.wordsfactory.network.RetrofitHelper
import com.example.task.wordsfactory.network.WordApi
import com.example.task.wordsfactory.data.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception


class RemoteDataSource {

    val service = RetrofitHelper.getInstance().create(WordApi::class.java)

    suspend fun getWord(searchWord: String): Result<Word> {
        return withContext(Dispatchers.IO) {
            try {
                println("---------------------------------")

                val result2 = service.getWord(searchWord)
                println(result2.body())

                println("---------------------------------")


                val result = service.getWord(searchWord).body()?.firstOrNull()

                if (result != null) {
                    Result.success(
                        result.toModelWithMeanings()
                    )
                } else {
                    Result.failure(Exception("xnj-nj gjikj yt nfr"))
                }
            } catch (io: IOException) {
                Result.failure(io)
            }

        }
    }


}
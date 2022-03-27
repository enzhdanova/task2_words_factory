package com.example.task.wordsfactory.data.dataSource

import com.example.task.wordsfactory.network.WordApi
import com.example.task.wordsfactory.network.entity.WordRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(WordApi::class.java)

    fun getWord(searchWord: String): Result<WordRequest> {
        var word: WordRequest
        var wordResult: Result<WordRequest> =  Result.failure(Exception("Что-то пошло не так"))
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                try {
                    println("_______service______ ${service}")
                    word = service.getWord(searchWord)

                    wordResult = Result.success(word)
                } catch (e: Exception) {
                    println(e)
                    wordResult = Result.failure(Exception("Что-то пошло не так"))
                }

            }
        }
        return wordResult
    }
}
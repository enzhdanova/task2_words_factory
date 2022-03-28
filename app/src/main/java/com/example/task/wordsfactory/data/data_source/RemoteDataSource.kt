package com.example.task.wordsfactory.data.data_source

import com.example.task.wordsfactory.network.WordApi
import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(WordApi::class.java)

    fun getWord(searchWord: String): Result<WordUI> = Result.failure(Exception("Ошибка"))


    fun getPartOfSpeech(id: Long): Result<String>  = Result.failure(Exception("Ошибка"))

    fun getMeaning(id: Long): Result<MeaningUI>  = Result.failure(Exception("Ошибка"))

}
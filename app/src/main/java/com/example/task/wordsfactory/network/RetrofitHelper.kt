package com.example.task.wordsfactory.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://api.dictionaryapi.dev/"

    fun getInstance(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

}
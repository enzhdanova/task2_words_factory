package com.example.task.wordsfactory.network

import com.example.task.wordsfactory.network.entity.WordRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WordApi {
    @GET("api/v2/entries/en/{searchWord}")
    suspend fun getWord(
       @Path("searchWord") searchWord: String
    ): Response<List<WordRequest>>
}
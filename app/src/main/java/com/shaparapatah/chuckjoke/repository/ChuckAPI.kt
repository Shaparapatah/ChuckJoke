package com.shaparapatah.chuckjoke.repository

import com.shaparapatah.chuckjoke.ServerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChuckAPI {

    @GET("jokes/random/{numberOfJokes}")
    fun getChuckJokes(
        @Path("numberOfJokes") numberOfJokes: Int
    ): Call<ServerResponse>
}
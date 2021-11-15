package com.shaparapatah.chuckjoke

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckAPI {

    @GET("jokes/random")
    fun getChuckJoke(
        @Query("joke") joke: String
    ): Call<List<Value>>
}
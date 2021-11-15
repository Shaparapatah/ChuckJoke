package com.shaparapatah.chuckjoke

import retrofit2.Call
import retrofit2.http.GET

interface ChuckAPI {

    @GET("jokes/random")
    fun getChuckJoke(): Call<Value>
}
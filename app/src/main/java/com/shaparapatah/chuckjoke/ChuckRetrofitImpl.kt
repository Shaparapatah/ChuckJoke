package com.shaparapatah.chuckjoke

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChuckRetrofitImpl {

    private val baseUrl = "https://api.icndb.com"

    fun getRetrofitImpl(): ChuckAPI {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ChuckAPI::class.java)
    }
}
package com.alxdthn.newstinkoff

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.tinkoff.ru/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}
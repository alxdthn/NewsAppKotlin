package com.alxdthn.newstinkoff.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
	@GET("news")
	fun getPosts(): Call<Answer>

	@GET("news_content/")
	suspend fun getContent(@Query("id", encoded = true) id: Int): Response<Content>
}
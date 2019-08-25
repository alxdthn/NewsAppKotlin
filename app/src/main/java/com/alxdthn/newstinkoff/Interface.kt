package com.alxdthn.newstinkoff

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("news")
    suspend fun getPosts(): Response<Post>

    @GET("news_content/")
    suspend fun getContent(@Query("id", encoded = true) id: Int): Response<Content>
}
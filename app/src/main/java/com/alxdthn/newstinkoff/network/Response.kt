package com.alxdthn.newstinkoff.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response

fun getResponse(service: RetrofitService) : List<Payload>? {


    Log.d("bestTAG", "try send response")
    val response = service.getPosts().enqueue(object: Callback<Answer> {
        override fun onFailure(call: Call<Answer>, t: Throwable) {
            Log.d("bestTAG", "error on response")
        }

        override fun onResponse(call: Call<Answer>?, response: Response<Answer>?) {
            Log.d("bestTAG", "on response..")
            response?.body()
        }
    })
    Log.d("bestTAG", "response is get")

    return news
}


package com.alxdthn.newstinkoff

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = RetrofitFactory.makeRetrofitService()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getPosts()
            runOnUiThread {
                val news = response.body()?.payload?.toList() ?: throw (Throwable("Error"))
                val myAdapter = MainAdapter(news, object : MainAdapter.Callback {
                   override fun onItemClicked(item: Payload) {
                       Log.d("click", item.id.toString())
                       CoroutineScope(Dispatchers.IO).launch {
                           val response = service.getContent(item.id)
                           runOnUiThread {
                               val content = response.body()?.payload ?: throw (Throwable("Error"))
                               val intent = Intent(this@MainActivity, NewsContent::class.java)
                               intent.putExtra("content", content.content)
                               startActivity(intent)
                           }
                       }
                    }
                })
                myRecycler.adapter = myAdapter
            }
        }
    }
}


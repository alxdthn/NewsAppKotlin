package com.alxdthn.newstinkoff.view

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alxdthn.newstinkoff.NewsContentActivity
import com.alxdthn.newstinkoff.network.ContentPayload
import com.alxdthn.newstinkoff.network.Payload
import com.alxdthn.newstinkoff.network.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun drawContent(content: ContentPayload?, activity: AppCompatActivity) {
	val intent = Intent(activity, NewsContentActivity::class.java)

	intent.putExtra("content", content?.content)
	intent.putExtra("title", content?.title?.text)
	activity.startActivity(intent)
}

fun viewProcess(news: List<Payload>, service: RetrofitService, activity: AppCompatActivity) {

	val myAdapter = MainAdapter(
		news,
		object : MainAdapter.Callback {
			override fun onItemClicked(item: Payload) {
				Log.d("click", item.id.toString())
				CoroutineScope(Dispatchers.Main).launch {
					try {
						val response = service.getContent(item.id)
						val content = response.body()?.payload
						activity.runOnUiThread {
							drawContent(
								content,
								activity
							)
						}
					} catch (e: Throwable) {
						alertWindow(activity, "No internet connection")
					}
				}
			}
		})
	activity.myRecycler.adapter = myAdapter
}
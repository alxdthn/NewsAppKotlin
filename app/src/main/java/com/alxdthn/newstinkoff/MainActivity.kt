package com.alxdthn.newstinkoff

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alxdthn.newstinkoff.data.AppDatabase
import com.alxdthn.newstinkoff.data.NewsEntity
import com.alxdthn.newstinkoff.network.Payload
import com.alxdthn.newstinkoff.network.RetrofitFactory
import com.alxdthn.newstinkoff.view.alertWindow
import com.alxdthn.newstinkoff.view.viewProcess
import kotlinx.coroutines.*

fun parseToEntity(db: AppDatabase, news: List<Payload>?) {

	var entity: NewsEntity

	if (news != null) {
		Log.d("bestTAG", "start parce")
		for (node in news) {
			entity = NewsEntity(
					node.id, node.name, node.text,
					node.publicationDate.milliseconds,
					node.bankInfoTypeId
			)
			db.make().insert(entity)
		}
		Log.d("bestTAG", "end parce")
	}
	else
		Log.d("bestTAG", "no response")
}

class MainActivity : AppCompatActivity()  {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val db = AppDatabase.invoke(this@MainActivity)
		val service = RetrofitFactory.makeRetrofitService()

		CoroutineScope(Dispatchers.IO).launch {
			try {
				parseToEntity(db, service.getPosts().body()?.payload?.toList())
			} catch (e: Throwable) {
				runOnUiThread {
					alertWindow(this@MainActivity, "no internet connection")
				}
			}
			runOnUiThread {
				viewProcess(db, service, this@MainActivity)
			}
		}
	}
}

/*
class MainActivity : AppCompatActivity()  {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val db = AppDatabase.invoke(this@MainActivity)
		val service = RetrofitFactory.makeRetrofitService()

		service.getPosts().enqueue(object : Callback<Answer> {
			override fun onFailure(call: Call<Answer>, t: Throwable) {
				Log.d("bestTAG", "response error")
			}

			override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
				parseToEntity(db, response.body()?.payload?.toList())
				Log.d("bestTAG", "response done")
				viewProcess(db, service, this@MainActivity)
			}
		})
	}
}
*/
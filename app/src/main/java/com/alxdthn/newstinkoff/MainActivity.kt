package com.alxdthn.newstinkoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alxdthn.newstinkoff.data.AppDatabase
import com.alxdthn.newstinkoff.data.NewsEntity
import com.alxdthn.newstinkoff.network.RetrofitFactory
import com.alxdthn.newstinkoff.view.alertWindow
import com.alxdthn.newstinkoff.view.viewProcess
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity()  {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		CoroutineScope(Dispatchers.IO).launch {
			val test = NewsEntity(1, 2, "name", "test", 123, 13)
			val db = AppDatabase.invoke(this@MainActivity)
			db.make().insert(test)
		}

		val service = RetrofitFactory.makeRetrofitService()

		CoroutineScope(Dispatchers.Main).launch {
			try {
				val response = service.getPosts()
				val news = response.body()?.payload?.toList() ?: throw (Throwable("Error"))
				runOnUiThread{ viewProcess(news, service, this@MainActivity) }
			} catch (e: Throwable) { alertWindow(this@MainActivity, "No internet connection") }
		}
	}
}


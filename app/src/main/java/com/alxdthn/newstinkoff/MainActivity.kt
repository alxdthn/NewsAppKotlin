package com.alxdthn.newstinkoff

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.io.IOException

class MainActivity : AppCompatActivity()  {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val service = RetrofitFactory.makeRetrofitService()

		CoroutineScope(Dispatchers.Main).launch {
			try {
				val response = service.getPosts()
				val news = response.body()?.payload?.toList() ?: throw (Throwable("Error"))
				runOnUiThread{ viewProcess(news, service, this@MainActivity) }
			} catch (e: Throwable) {
				alertWindow(this@MainActivity, "No internet connection")
			}
		}
	}
}


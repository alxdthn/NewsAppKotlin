package com.alxdthn.newstinkoff

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.alxdthn.newstinkoff.data.AppDatabase
import com.alxdthn.newstinkoff.data.NewsEntity
import com.alxdthn.newstinkoff.network.Payload
import com.alxdthn.newstinkoff.network.RetrofitFactory
import com.alxdthn.newstinkoff.network.getResponse
import com.alxdthn.newstinkoff.view.alertWindow
import com.alxdthn.newstinkoff.view.viewProcess
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Response

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

		parseToEntity(db, getResponse(service))
		viewProcess(db, service, this@MainActivity)
	}
}

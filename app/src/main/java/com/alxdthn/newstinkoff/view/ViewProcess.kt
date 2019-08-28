package com.alxdthn.newstinkoff.view

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alxdthn.newstinkoff.NewsContentActivity
import com.alxdthn.newstinkoff.R
import com.alxdthn.newstinkoff.data.AppDatabase
import com.alxdthn.newstinkoff.data.NewsEntity
import com.alxdthn.newstinkoff.network.ContentPayload
import com.alxdthn.newstinkoff.network.RetrofitService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun drawContent(content: ContentPayload?, activity: AppCompatActivity) {
	val intent = Intent(activity, NewsContentActivity::class.java)

	intent.putExtra("content", content?.content)
	intent.putExtra("title", content?.title?.text)
	activity.startActivity(intent)
}

fun viewProcess(db: AppDatabase, service: RetrofitService, activity: AppCompatActivity) {

    activity.myRecycler.adapter = MainAdapter(
            db.make().getAll(),
		object : MainAdapter.Callback {

			override fun onItemClicked(item: NewsEntity) {
				Log.d("click", item.id.toString())
				CoroutineScope(Dispatchers.IO).launch {
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
		}
    )
    val swipeContainer: SwipeRefreshLayout = activity.findViewById(R.id.refresh)

    swipeContainer.setOnRefreshListener {
        Log.d("bestTAG", "refresh!")
		val test = NewsEntity(1, "name", "text", 12, 2)

        (activity.myRecycler.adapter as MainAdapter).updateData(mutableListOf(test))
		activity.myRecycler.adapter?.notifyDataSetChanged()
        swipeContainer.isRefreshing = false
    }

    swipeContainer.setColorSchemeColors(
            activity.resources.getColor(R.color.tinkoffYellow),
            activity.resources.getColor(R.color.design_default_color_primary),
            activity.resources.getColor(R.color.design_default_color_primary_dark),
            activity.resources.getColor(R.color.tinkoffYellow)
    )
}
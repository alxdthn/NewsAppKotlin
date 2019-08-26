package com.alxdthn.newstinkoff

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.content_scrolling.*

class NewsContent : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_scrolling)

		val toolbar: Toolbar = findViewById(R.id.toolbar)
		toolbar.title = intent.getStringExtra("title")
		setSupportActionBar(toolbar)
		webView.loadData(intent.getStringExtra("content"), "text/html; charset=UTF-8", null)
	}
}

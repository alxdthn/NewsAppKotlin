package com.alxdthn.newstinkoff.view

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.finishAffinity

fun	alertWindow(activity: AppCompatActivity, message: String) {
	try {
		val alertDialogBuilder = AlertDialog.Builder(activity)

		alertDialogBuilder.setTitle("TinkoffNews")
		alertDialogBuilder.setMessage(message)
		alertDialogBuilder.show()
		Log.d("DEBUG", "alert: $message")
	} catch (e: Throwable) {
		finishAffinity(activity)
	}
}
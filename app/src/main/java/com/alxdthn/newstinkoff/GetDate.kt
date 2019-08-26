package com.alxdthn.newstinkoff

import java.text.SimpleDateFormat
import java.util.*


fun getDate(milliSeconds: Long, dateFormat: String): String {
	// Create a DateFormatter object for displaying date in specified format.
	val formatter = SimpleDateFormat(dateFormat)

	val calendar = Calendar.getInstance()
	calendar.timeInMillis = milliSeconds
	return formatter.format(calendar.time)
}
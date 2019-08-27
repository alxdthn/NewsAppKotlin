package com.alxdthn.newstinkoff.network

import java.text.SimpleDateFormat
import java.util.*

fun getDate(milliSeconds: Long, dateFormat: String): String {
	val formatter = SimpleDateFormat(dateFormat, Locale.ENGLISH)

	val calendar = Calendar.getInstance()
	calendar.timeInMillis = milliSeconds
	return formatter.format(calendar.time)
}
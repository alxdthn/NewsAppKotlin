package com.alxdthn.newstinkoff

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun html2Text(html: String): Spanned {
	return HtmlCompat.fromHtml(html,
		HtmlCompat.FROM_HTML_MODE_LEGACY)
}
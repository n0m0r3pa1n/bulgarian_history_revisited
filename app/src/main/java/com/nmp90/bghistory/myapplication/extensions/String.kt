package com.nmp90.bghistory.myapplication.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned

fun String?.isNullOrEmpty() : Boolean {
    return this == null || this.isEmpty()
}

fun String?.fromHtml() : Spanned {
    if (this == null) {
        return getHtml("")
    }

    return getHtml(this)
}

private fun getHtml(text: String): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
    } else {
        return Html.fromHtml(text)
    }
}

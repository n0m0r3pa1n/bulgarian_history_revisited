package com.nmp90.bghistory.compose.html

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.nmp90.bghistory.R

@Composable
fun HtmlText(
    html: String,
    isInDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            TextView(context).apply {
                setTextColor(
                    ContextCompat.getColor(
                        context,
                        if (isInDarkTheme) {
                            R.color.white
                        } else {
                            R.color.black
                        }
                    )
                )
            }
        },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}
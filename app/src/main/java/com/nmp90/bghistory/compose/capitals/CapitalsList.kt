package com.nmp90.bghistory.compose.capitals

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CapitalsList(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Georgi!",
        modifier = modifier.fillMaxHeight().fillMaxWidth()
    )
}
package com.nmp90.bghistory.compose.errors

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nmp90.bghistory.R

@Composable
fun ErrorDialog(shouldOpenAlertDialog: Boolean = true) {
    val openAlertDialog = remember { mutableStateOf(shouldOpenAlertDialog) }
    if (openAlertDialog.value) {
        AlertDialog(
            text = {
                Text(text = stringResource(id = R.string.error))
            },
            onDismissRequest = { openAlertDialog.value = false },
            confirmButton = {
                Text(text = stringResource(id = R.string.btn_ok), modifier = Modifier.clickable {
                    openAlertDialog.value = false
                })
            },
        )
    }
}
package com.nmp90.bghistory

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import com.crashlytics.android.Crashlytics

class ErrorHandler {
    fun handleError(context: Context, ex: Throwable) {
        if (!BuildConfig.DEBUG) {
            Crashlytics.logException(ex)
        }
        Log.e("Error", "Error happened",ex)

        AlertDialog.Builder(context)
            .setMessage(R.string.error)
            .setPositiveButton(R.string.btn_ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}

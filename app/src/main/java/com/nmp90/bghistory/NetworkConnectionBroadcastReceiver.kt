package com.nmp90.bghistory

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkConnectionBroadcastReceiver : BroadcastReceiver() {

    private var alertDialog: AlertDialog? = null
    private var wasDialogShown = false

    override fun onReceive(context: Context, intent: Intent?) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (!isConnected && !wasDialogShown) {
            alertDialog = AlertDialog.Builder(context)
                .setMessage(R.string.no_internet)
                .setPositiveButton(R.string.btn_ok) { dialog, _ -> dialog.dismiss() }
                .create()
            alertDialog?.show()
            wasDialogShown = true
        } else {
            alertDialog?.dismiss()
        }
    }
}

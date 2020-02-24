package com.pmesa.chiperreddit.view.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ChiperConnectivityManager(context: Context) {

    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    private val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

    fun getStatus(callback: (Status) -> Unit) {
        callback(Status(isConnected, activeNetwork?.typeName ?: ""))
    }

}

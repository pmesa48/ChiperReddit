package com.pmesa.chiperreddit.view.connectivity

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pmesa.chiperreddit.view.connectivity.NetworkStateReceiver.NetworkStateReceiverListener


abstract class ConnectivityActivity : AppCompatActivity(), NetworkStateReceiverListener {

    private var networkStateReceiver // Receiver that detects network state changes
            : NetworkStateReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startNetworkBroadcastReceiver(this)
    }

    override fun onPause() {
        unregisterNetworkBroadcastReceiver(this)
        super.onPause()
    }

    override fun onResume() {
        registerNetworkBroadcastReceiver(this)
        super.onResume()
    }

    override fun networkAvailable(){}

    override fun networkUnavailable(){}

    open fun startNetworkBroadcastReceiver(currentContext: Context) {
        networkStateReceiver = NetworkStateReceiver()
        networkStateReceiver!!.addListener((currentContext as NetworkStateReceiverListener))
        registerNetworkBroadcastReceiver(currentContext)
    }

    /**
     * Register the NetworkStateReceiver with your activity
     * @param currentContext
     */
    open fun registerNetworkBroadcastReceiver(currentContext: Context) {
        currentContext.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    /**
     * Unregister the NetworkStateReceiver with your activity
     * @param currentContext
     */
    open fun unregisterNetworkBroadcastReceiver(currentContext: Context) {
        currentContext.unregisterReceiver(networkStateReceiver)
    }
}
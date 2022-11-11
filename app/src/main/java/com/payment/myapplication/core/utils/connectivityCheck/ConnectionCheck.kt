package com.payment.myapplication.core.utils.connectivityCheck

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

fun getCurrentConnectivityState(
    connectivityManager: ConnectivityManager
): ConnectionState {
    var connected = false
    connectivityManager.allNetworkInfo.forEach {
        if(it.state == NetworkInfo.State.CONNECTED) {
            connected = true
        }
    }

    return if (connected) ConnectionState.Available else ConnectionState.Unavailable
}
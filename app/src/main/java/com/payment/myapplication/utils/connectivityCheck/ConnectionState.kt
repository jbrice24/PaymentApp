package com.payment.myapplication.utils.connectivityCheck

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}

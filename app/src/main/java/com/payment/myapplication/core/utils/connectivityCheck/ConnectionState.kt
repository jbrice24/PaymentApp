package com.payment.myapplication.core.utils.connectivityCheck

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}

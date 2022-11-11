package com.payment.myapplication.data.sourceFactory

import com.payment.myapplication.data.source.Remote
import javax.inject.Inject

class DataSourceFactory @Inject constructor(private val remote: Remote) {

    fun getRemote() = remote

}
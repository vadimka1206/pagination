package com.example.n1pagination.data.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkState(private val context: Context) {

    fun hasOnlineNetwork(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork.isConnected
    }
}
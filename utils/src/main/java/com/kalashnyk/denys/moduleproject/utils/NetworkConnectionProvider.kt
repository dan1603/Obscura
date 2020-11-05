package com.kalashnyk.denys.moduleproject.utils


import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager

interface NetworkConnectionProvider {
    fun listenConnectionChanges(listener: NetworkConnectionListener)
    fun ignoreConnectionChanges(listener: NetworkConnectionListener)
}

interface NetworkConnectionListener {
    fun onNetworkConnectionLost()
    fun onNetworkConnectionRestored()
}

class NetworkConnectionProviderImpl(val activity: Activity)
    : BroadcastReceiver(), NetworkConnectionProvider {

    private val listeners: MutableList<NetworkConnectionListener> = mutableListOf()
    private var hadConnection = true
    private var receiverRegistered: Boolean = false

    private fun registerReceiver() {
        activity.registerReceiver(
            this,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        receiverRegistered = true
    }

    private fun unregisterReceiver() {
        if (receiverRegistered) {
            activity.unregisterReceiver(this)
            receiverRegistered = false
        }
    }

    private fun unregisterReceiverIfNoListeners() {
        if (listeners.isEmpty()) {
            unregisterReceiver()
        }
    }

    fun destroy() {
        unregisterReceiver()
    }

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val connectedNow = networkInfo != null && networkInfo.isConnectedOrConnecting
        if (!connectedNow) {
            listeners.forEach { it.onNetworkConnectionLost() }
            hadConnection = false
        } else if (!hadConnection) {
            listeners.forEach { it.onNetworkConnectionRestored() }
            hadConnection = true
        }
    }

    override fun listenConnectionChanges(listener: NetworkConnectionListener) {
        registerReceiver()
        listeners.add(listener)
    }

    override fun ignoreConnectionChanges(listener: NetworkConnectionListener) {
        listeners.remove(listener)
        unregisterReceiverIfNoListeners()
    }
}

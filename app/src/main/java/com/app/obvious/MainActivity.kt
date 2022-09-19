package com.app.obvious

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.obvious.utils.ConnectivityReceiver
import com.app.obvious.utils.showToast

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var isInternetAvailable = true
    private lateinit var intentFilter: IntentFilter
    private lateinit var receiver: ConnectivityReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize Connectivity receiver to update internet status
        intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        receiver = ConnectivityReceiver()


    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, intentFilter)
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    //Checking internet isInternetAvailable using broadcast receiver
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isInternetAvailable != isConnected) {
            if (isConnected) {
                showToast("Connected to internet")
            } else {
                showToast("No Internet")
            }
        }
        isInternetAvailable = isConnected
    }

    override fun onStop() {
        try {
            unregisterReceiver(receiver)
        } catch (_: Exception) { }
        super.onStop()
    }
}

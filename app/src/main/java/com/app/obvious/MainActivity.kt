package com.app.obvious

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.obvious.databinding.ActivityMainBinding
import com.app.obvious.ui.fragments.GridFragment
import com.app.obvious.utils.ConnectivityReceiver
import com.app.obvious.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var isInternetAvailable = true
    private lateinit var intentFilter: IntentFilter
    private lateinit var receiver: ConnectivityReceiver
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Initialize Connectivity receiver to update internet status
        intentFilter = IntentFilter().apply {
            addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        }
        receiver = ConnectivityReceiver()
        setupViews()
    }

    private fun setupViews() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, GridFragment.newInstance())
            .commit()
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
                showToast(getString(R.string.connected_to_internet))
            } else {
                showToast(getString(R.string.no_internet))
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

package com.ttc.finch_station

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ttc.finch_station.data.base.BaseActivity
import com.ttc.finch_station.data.common.logDebug
import com.ttc.finch_station.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TtcActivity : BaseActivity() {

    lateinit var broadcastManager: LocalBroadcastManager

    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar


    companion object {
        public val NETWORK_CONNECTED = "NETWORK_CONNECTED"
        public val NETWORK_DISCONNECTED = "NETWORK_DISCONNECTED"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logDebug {}
        setupUI()

        broadcastManager = LocalBroadcastManager.getInstance(this)
    }

    private fun setupUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        toolbar = binding.myToolbar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.myToolbar)
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        logDebug {}
    }

    override fun onStop() {
        super.onStop()
        logDebug {}
    }

    override fun onDestroy() {
        super.onDestroy()
        logDebug {}
    }

    override fun onInternetConnect() {
        logDebug {}
        sendBroadcast(Intent(NETWORK_CONNECTED))
    }

    override fun onInternetDisconnect() {
        logDebug {}
        sendBroadcast(Intent(NETWORK_DISCONNECTED))
    }


}
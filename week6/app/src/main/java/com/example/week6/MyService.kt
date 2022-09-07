package com.example.week6

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

private const val MY_ACTION = "MyAction"

class MyService : Service() {

    val manager by lazy { LocalBroadcastManager.getInstance(this) }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()
        val br = MyReceiver()
        val filter = IntentFilter(MY_ACTION)

        manager.registerReceiver(br, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { processCommand(it) } ?: return START_STICKY
        return super.onStartCommand(intent, flags, startId)
    }

    private fun processCommand(intent: Intent) {
        val text = intent.getStringExtra(EXTRA_NAME)
        Toast.makeText(this, "Service", Toast.LENGTH_SHORT).show()
        val sendIntent = Intent(MY_ACTION).also {
            it.putExtra(EXTRA_NAME, text)
            manager.sendBroadcast(it)
        }
    }
}
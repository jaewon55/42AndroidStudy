package com.example.week6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Receiver", Toast.LENGTH_SHORT).show()
        val text = intent.getStringExtra(EXTRA_NAME) ?: "fail"
        sendToActivity(context, text)
    }

    private fun sendToActivity(context: Context, text: String) {
        val intent = Intent(context, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra(EXTRA_NAME, text)

        context.startActivity(intent)
    }
}
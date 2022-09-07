package com.example.week6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week6.databinding.ActivityMainBinding

const val EXTRA_NAME = "text"

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            val sendText = binding.editText.text.toString()

            intent.putExtra(EXTRA_NAME, sendText)
            startService(intent)
        }

    }

    override fun onNewIntent(intent: Intent?) {
        val text = intent?.getStringExtra(EXTRA_NAME) ?: ""
        binding.textView.text = text
        super.onNewIntent(intent)
    }
}
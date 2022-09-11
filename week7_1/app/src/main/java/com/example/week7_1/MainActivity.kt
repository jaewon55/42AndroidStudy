package com.example.week7_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week7_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text = "0명"

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MyAdapter(mutableListOf())
        binding.recyclerView.adapter = adapter

        binding.add.setOnClickListener {
            val inputName = binding.name.text.toString()
            val inputBirth = binding.birth.text.toString()
            val inputPhoneNumber = binding.phoneNumber.text.toString()
            val isEmpty = when {
                inputName.isEmpty() -> makeToast("이름을 입력해 주세요.")
                inputBirth.isEmpty() -> makeToast("생년월일을 입력해 주세요.")
                inputPhoneNumber.isEmpty() -> makeToast("전화번호를 입력해 주세요.")
                else -> false
            }
            if (!isEmpty) {
                val customer = Customer(inputName, inputBirth, inputPhoneNumber)
                adapter.items.add(customer)
                adapter.notifyItemInserted(adapter.items.size - 1)
                val text = "${adapter.items.size}명"
                binding.textView.text = text
            }
        }
    }

    private fun makeToast(message: String): Boolean {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return true
    }
}
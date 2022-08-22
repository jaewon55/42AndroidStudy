package com.example.week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week4.databinding.ActivityManagementBinding

class ManagementActivity : AppCompatActivity() {
    private val binding by lazy { ActivityManagementBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val activity = setPageName()
        binding.goLogin.setOnClickListener { buttonClick(-1) }
        binding.goMenu.setOnClickListener { buttonClick(activity) }
    }

    override fun onBackPressed() {
        buttonClick(-1)
    }

    private fun setPageName(): Int {
        val intent = intent
        val bundle = intent.extras
        val activity = bundle?.getInt("activity") ?: -1
        binding.textView.text = when (activity) {
            CUSTOMER_ACTIVITY -> "고객 관리"
            SALES_ACTIVITY -> "매출 관리"
            else -> "상품 관리"
        }
        Toast.makeText(this, "from : 메인 메뉴", Toast.LENGTH_SHORT).show()
        return activity
    }

    private fun buttonClick(activity: Int) {
        val intent = intent
        if (activity > 0) {
            intent.putExtra("activity", activity)
            setResult(RESULT_OK, intent)
        } else {
            setResult(RESULT_CANCELED, intent)
        }
        finish()
    }
}
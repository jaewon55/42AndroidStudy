package com.example.week4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week4.databinding.ActivityMenuBinding

const val CUSTOMER_ACTIVITY = 1
const val SALES_ACTIVITY = 2
const val PRODUCT_ACTIVITY = 3
const val MAINMENU_ACTIVITY = 4

class MenuActivity : AppCompatActivity() {
    private val binding: ActivityMenuBinding by lazy { ActivityMenuBinding.inflate(layoutInflater) }
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initResultLauncher()
        helloUser()
        clickManagementButton()
    }

    override fun onBackPressed() {
        val intent = intent
        intent.putExtra("activity", MAINMENU_ACTIVITY)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        val pref = getSharedPreferences("pref", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    helloUser()
                } else {
                    val activity = result.data?.getIntExtra("activity", -1)
                    val intent = intent
                    intent.putExtra("activity", activity)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
    }


    private fun helloUser() {
        val pref = getSharedPreferences("user", Activity.MODE_PRIVATE)
        val userId: String =
            if (pref.contains("user")) {
                pref.getString("user", "") ?: ""
            } else {
                val intent = intent
                val bundle = intent.extras
                val data: User = bundle?.getParcelable("user") ?: return (loginFail())
                val editor = pref.edit()

                editor.putString("name", data.id)
                editor.apply()
                data.id
            }
        Toast.makeText(this, "$userId 님 반갑습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun loginFail() {
        val intent = intent
        setResult(RESULT_CANCELED, intent)
        Toast.makeText(this, "로그인이 실패하였습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun clickManagementButton() {
        binding.customerBtn.setOnClickListener { startManagementActivity(CUSTOMER_ACTIVITY) }
        binding.salesBtn.setOnClickListener { startManagementActivity(SALES_ACTIVITY) }
        binding.productBtn.setOnClickListener { startManagementActivity(PRODUCT_ACTIVITY) }
    }

    private fun startManagementActivity(id: Int) {
        val intent = Intent(this, ManagementActivity::class.java)
        intent.putExtra("activity", id)
        resultLauncher.launch(intent)
    }


}

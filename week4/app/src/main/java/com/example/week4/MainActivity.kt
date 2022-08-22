package com.example.week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
//    java.lang.IllegalStateException: LifecycleOwner com.example.week4.MainActivity@9fe7d7d is attempting to register while current state is RESUMED.
//    LifecycleOwners must call register before they are STARTED.
//    private val resultLauncher by lazy { ... }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initResultLauncher()
        clickLoginBtn()
    }

    private fun initResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK)
                    resultOk(result)
            }
    }

    private fun resultOk(result: ActivityResult) {
        val activity = when (result.data?.getIntExtra("activity", -1)) {
            CUSTOMER_ACTIVITY -> "고객 관리"
            SALES_ACTIVITY -> "매출 관리"
            PRODUCT_ACTIVITY -> "상품 관리"
            else -> "메인 메뉴"
        }
        Toast.makeText(
            this,
            "from : $activity",
            Toast.LENGTH_SHORT
        ).show()
    }

    //manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    private fun clickLoginBtn() {
        val loginText = binding.loginText.text
        val passwordText = binding.passwordText.text
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.loginButton.setOnClickListener {
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            if (loginText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "아이디 또는 비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MenuActivity::class.java)
                val data = User(loginText.toString(), passwordText.toString())
                intent.putExtra("user", data)
                binding.loginText.text.clear()
                binding.passwordText.text.clear()
                resultLauncher.launch(intent)
            }

        }
    }

}

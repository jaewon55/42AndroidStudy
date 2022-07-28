package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.week3.databinding.FragmentLandTestBinding

/*
Configuration Change
configuration change는 장치가 급격하게 변화하는 경우 일어난다. 이 때 system이 변화에 가장 쉽게 대응하는 방법은 activity를 강제종료하고 재시작하는 것이다.
configuration change 예시
    - 장치의 언어를 변경한 경우
    - 외부 장치를 연결해 다른 layout을 적용해야 하는 경우
    - 화면이 회전하는 경우
activity가 완전히 종료되기 때문에 onDestroy()메서드가 호출되고 app의 resourse가 메모리에서 삭제된다. 즉 저장되지 않은 data는 잃어버리게 된다.
화면이 회전할 때 마다 data가 삭제된다면 사용자는 큰 불편을 겪게 되기 때문에 이를 방지하기 위한 방법으로 onSaveInstanceState()메서드를 사용할 수 있다.

onSaveInstanceState() - 데이터 저장
onSaveInstanceState()는 앱이 종료될 때 필요한 데이터를 저장하는 메서드로 onStop()이후에 호출된다. 즉 background에 들어가는 시점에 호출되는 것이다.
Android는 bundle에 key-value방식으로 저장한다. key는 항상 string형이고 value는 int 또는 boolean형으로 저장하는 것이 바람직 하다.
    - bundle은 RAM에 저장되기 때문에 data를 작게 저장하는 것이 좋다.
    - 일반적으로 100KB이하로 저장하는 것이 권장되고 그렇지 않으면 TransactionTooLargeException에러가 발생할 수 있다.

데이터 복원
데이터 복원은 onCreate()메서드에서 이루어 진다.
*/

private lateinit var binding: FragmentLandTestBinding

class LandTest : Fragment() {
    private lateinit var textView: TextView
    private lateinit var upBtn: Button
    private var textNum = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 데이터 복원
        restoreState(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandTestBinding.inflate(inflater, container, false)
        textView = binding.textView
        upBtn = binding.upBtn
        textView.text = textNum.toString()
        upBtn.setOnClickListener { pressUpBtn() }
        return binding.root
    }

    // 데이터 저장
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("textNum", textNum)
    }

    private fun restoreState(bundle: Bundle?) {
        if (bundle == null) return
        textNum = bundle.getInt("textNum")
    }

    private fun pressUpBtn() {
        textNum++
        textView.text = textNum.toString()
    }
}



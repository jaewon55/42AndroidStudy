package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.week3.databinding.FragmentMissionTwoBinding

private lateinit var binding: FragmentMissionTwoBinding

class MissionTwo : Fragment() {
    private lateinit var textView: TextView
    private lateinit var editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMissionTwoBinding.inflate(inflater, container, false)
        textChanged()
        setButtonListener()
        return binding.root
    }

    private fun textChanged() {
        editText = binding.editText
        textView = binding.letterCnt
        editText.addTextChangedListener {
            val text = "${editText.text.length} / 80 바이트"
            textView.text = text
        }
    }

    private fun setButtonListener() {
        binding.sendBtn.setOnClickListener {
            val toastMessage =
                if (editText.text.isEmpty()) "메세지를 입력해주세요" else editText.text.toString()
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            editText.text.clear()
        }

        binding.closeBtn.setOnClickListener {
            editText.text.clear()
        }
    }

}
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
    private lateinit var editText: EditText
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMissionTwoBinding.inflate(inflater, container, false)
        editText = binding.editText
        textView = binding.letterCnt
        editText.addTextChangedListener { countLetter() }
        binding.sendBtn.setOnClickListener { sendMessage() }
        return binding.root
    }

    private fun countLetter() {
        val text = "${editText.text.length} / 80 바이트"
        textView.text = text
    }

    private fun sendMessage() {
        var toastMessage: String
        if (editText.text.isEmpty())
            toastMessage = "메세지를 입력해주세요"
        else
            toastMessage = editText.text.toString()
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        editText.text.clear()
    }

}
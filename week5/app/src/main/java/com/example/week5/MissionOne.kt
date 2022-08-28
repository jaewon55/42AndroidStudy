package com.example.week5

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.week5.databinding.FragmentMissionOneBinding
import java.text.SimpleDateFormat
import java.util.*


class MissionOne : Fragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentMissionOneBinding
    private lateinit var dateText: TextView
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionOneBinding.inflate(inflater, container, false)
        setDateToday()
        saveButtonListener()
        nextButtonListener()
        return binding.root
    }

    private fun setDateToday() {
        dateText = binding.dateText
        dateText.inputType = InputType.TYPE_NULL
        val now = System.currentTimeMillis()
        dateText.text = SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(now)
        dateText.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        dateText.setOnClickListener {
            val picker = DatePickerDialog(
                requireContext(),
                this,
                year, month, day
            )
            picker.show()
        }
    }

    private fun saveButtonListener() {
        val name = binding.nameText
        val age = binding.ageText
        binding.saveButton.setOnClickListener {
            val message = if (name.text.isEmpty()) {
                "이름을 입력해 주세요."
            } else if (age.text.isEmpty()) {
                "나이를 입력해 주세요."
            } else {
                name.text.toString() + "|" + age.text.toString() + "|" + dateText.text
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun nextButtonListener() {
        binding.nextButton.setOnClickListener {
            mainActivity.goActivity2()
        }
    }

    override fun onDateSet(p0: DatePicker?, y: Int, m: Int, d: Int) {
        val date = "$y-${m + 1}-$d"
        dateText.text = date
    }
}
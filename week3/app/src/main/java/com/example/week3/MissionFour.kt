package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.week3.databinding.FragmentMissionFourBinding


private lateinit var binding: FragmentMissionFourBinding

class MissionFour : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var seekBar: SeekBar
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionFourBinding.inflate(inflater, container, false)
        setSeekBarChangeListener()
        return binding.root
    }

    /*
    onProgressChanged: 시크바를 조작하고 있는 중에 발생
    onStartTrackingTouch: 시크바를 처음 터치했을 때 발생
    onStopTrackingTouch: 시크바 터치가 끝났을 때 발생
     */
    private fun setSeekBarChangeListener() {
        seekBar = binding.seekBar
        progressBar = binding.progressBar
        textView = binding.countText
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                progressBar.progress = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                val msg = "값을 변경합니다."
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                textView.text = p0?.progress.toString()
            }
        })
    }

}
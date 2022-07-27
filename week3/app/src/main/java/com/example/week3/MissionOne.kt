package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import com.example.week3.databinding.FragmentMissionOneBinding

private lateinit var binding: FragmentMissionOneBinding

class MissionOne : Fragment() {
    private var imageIndex = 0
    private lateinit var scrollView: HorizontalScrollView
    private lateinit var scrollView2: HorizontalScrollView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionOneBinding.inflate(inflater, container, false)
        scrollView = binding.mOneScroll
        scrollView2 = binding.mOneScroll2
        binding.mOneButton.setOnClickListener { changeImage() }
        return binding.root
    }

    private fun changeImage() {
        if (imageIndex == 0) {
            scrollView.visibility = View.INVISIBLE
            scrollView2.visibility = View.VISIBLE
            imageIndex = 1
        } else if (imageIndex == 1) {
            scrollView.visibility = View.VISIBLE
            scrollView2.visibility = View.INVISIBLE
            imageIndex = 0
        }
    }

}
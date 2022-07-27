package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.week3.databinding.FragmentFrameLayoutTestBinding

private lateinit var binding: FragmentFrameLayoutTestBinding

class FrameLayoutTest : Fragment() {
    lateinit var imageView: ImageView
    lateinit var imageView2: ImageView
    var imageIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFrameLayoutTestBinding.inflate(inflater, container, false)
        imageView = binding.imageView
        imageView2 = binding.imageView2
        binding.button.setOnClickListener { changeImage() }
        return binding.root
    }

    private fun changeImage() {
        if (imageIndex == 0) {
            imageView.visibility = View.VISIBLE
            imageView2.visibility = View.INVISIBLE
            imageIndex = 1
        } else if (imageIndex == 1) {
            imageView.visibility = View.INVISIBLE
            imageView2.visibility = View.VISIBLE
            imageIndex = 0
        }
    }
}
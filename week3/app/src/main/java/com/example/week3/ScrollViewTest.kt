package com.example.week3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.week3.databinding.FragmentScrollViewTestBinding

private lateinit var binding: FragmentScrollViewTestBinding

class ScrollViewTest : Fragment() {
    lateinit var imageView: ImageView
    private var imageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScrollViewTestBinding.inflate(inflater, container, false)
        imageView = binding.scrollImage
        binding.button2.setOnClickListener { changeImage() }
        return binding.root
    }

    private fun changeImage() {
        if (imageIndex == 0) {
            imageView.setImageResource(R.drawable.image02)
            imageIndex = 1
        } else if (imageIndex == 1) {
            imageView.setImageResource(R.drawable.image01)
            imageIndex = 0
        }
    }
}
package com.example.week5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week5.databinding.Fragment3Binding

class Fragment3 : Fragment() {
    private lateinit var binding: Fragment3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment3Binding.inflate(inflater, container, false)
        return binding.root
    }
}
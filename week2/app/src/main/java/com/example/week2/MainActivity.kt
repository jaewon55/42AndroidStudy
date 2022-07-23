package com.example.week2

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.week2.databinding.ActivityMainBinding


private const val NUM_PAGES = 3
private lateinit var binding: ActivityMainBinding
private lateinit var pager: ViewPager2


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pager = binding.viewpager
        pager.setPageTransformer(ZoomOutPageTransformer())
        val adapter = PagerAdapter(this)
        pager.adapter = adapter
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0)
            super.onBackPressed()
        else
            pager.currentItem--
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> MissionOneFragment()
            1 -> MissionTwoFragment()
            else -> MissionThreeFragment()
        }
    }

}
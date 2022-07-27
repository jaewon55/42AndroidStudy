package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.week3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 4
private lateinit var binding: ActivityMainBinding
private lateinit var pager: ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewPagerApply()
    }

    private fun viewPagerApply() {
        pager = binding.viewpager
        val adapter = PagerAdapter(this)
        pager.adapter = adapter
        pager.isUserInputEnabled = false
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = "page ${(position + 1)}"
        }.attach()
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) super.onBackPressed()
        else pager.currentItem--
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> FrameLayoutTest()
            1 -> ScrollViewTest()
            2 -> MissionOne()
            else -> MissionTwo()
        }
    }
}
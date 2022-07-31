package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.week3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


private const val NUM_PAGES = 7
private const val BACK_PRESS_MESSAGE = "'뒤로' 버튼을 한번 더 누르시면 종료됩니다."
private lateinit var binding: ActivityMainBinding
private lateinit var pager: ViewPager2
private var isTerminate = false

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
            tab.text = "PAGE ${(position + 1)}"
        }.attach()
    }

    override fun onBackPressed() {
        if (pager.currentItem > 0) {
            pager.currentItem--
        } else if (!isTerminate) {
            Toast.makeText(this, BACK_PRESS_MESSAGE, Toast.LENGTH_SHORT).show()
            isTerminate = true
            launch {
                delay(2500)
                isTerminate = false
            }
        } else {
            super.onBackPressed()
        }
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> FrameLayoutTest()
            1 -> ScrollViewTest()
            2 -> MissionOne()
            3 -> MissionTwo()
            4 -> LandTest()
            5 -> MissionThree()
            else -> MissionFour()
        }

    }
}
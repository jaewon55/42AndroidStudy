package com.example.week5

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.week5.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    FragmentCallback {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var fragment1: Fragment1
    private lateinit var fragment2: Fragment2
    private lateinit var fragment3: Fragment3
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        fragment1 = Fragment1()
        fragment2 = Fragment2()
        fragment3 = Fragment3()

        supportFragmentManager.beginTransaction().replace(R.id.content_main, fragment1)
            .commit()

        binding.appBarMain.contentMain.bottomNavigation.setOnItemSelectedListener { item ->
            val selected = when (item.itemId) {
                R.id.tab1 -> fragment1
                R.id.tab2 -> fragment2
                else -> fragment3
            }
            supportFragmentManager.beginTransaction().replace(R.id.content_main, selected)
                .commit()
            true
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> onFragmentSelected(0, null)
            R.id.nav_gallery -> onFragmentSelected(1, null)
            R.id.nav_slideshow -> onFragmentSelected(2, null)
        }
        return true
    }

    override fun onFragmentSelected(position: Int, bundle: Bundle?) {
        val selected = when (position) {
            0 -> {
                toolbar.title = "첫 번재 화면"
                fragment1
            }
            1 -> {
                toolbar.title = "두 번재 화면"
                fragment2
            }
            else -> {
                toolbar.title = "세 번째 화면"
                fragment3
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.content_main, selected)
            .commit()
    }
}
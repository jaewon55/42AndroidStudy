package com.example.week5

import android.os.Bundle

interface FragmentCallback {
    fun onFragmentSelected(position: Int, bundle: Bundle?)
}
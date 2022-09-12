package com.example.week7_2

import android.view.View

interface OnProductItemClickListener {
    fun onItemClick(holder: MyAdapter.ViewHolder, view: View, position: Int)
}
package com.example.week4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// https://developer.android.com/kotlin/parcelize

@Parcelize
class User(val id: String, val password: String) : Parcelable
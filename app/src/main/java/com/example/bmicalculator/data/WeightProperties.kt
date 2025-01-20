package com.example.bmicalculator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeightProperties(val type: String, val weight: Int, val height: Int) : Parcelable

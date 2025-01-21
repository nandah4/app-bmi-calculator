package com.example.bmicalculator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeightProperties(val type: String, val weight: Double, val height: Double) : Parcelable

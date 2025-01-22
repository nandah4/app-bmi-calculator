package com.example.bmicalculator.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultProperties(
    val type: String,
    val height: Double,
    val weight: Double,
    val weightResult: Double,
    val categoryResult: String,
    val motivation: String
) : Parcelable

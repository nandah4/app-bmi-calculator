package com.example.bmicalculator.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.data.ResultProperties
import com.example.bmicalculator.data.WeightProperties
import com.example.bmicalculator.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    companion object {
        const val EXTRA_WEIGHT_PROPERTIES = "extra_weight_properties"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val data: WeightProperties? = if (Build.VERSION.SDK_INT >= 33) {
                bundle.getParcelable(EXTRA_WEIGHT_PROPERTIES, WeightProperties::class.java)
            } else {
                bundle.getParcelable(EXTRA_WEIGHT_PROPERTIES)
            }

            val (type, height, weight, weightResult, categoryResult, motivationResult) = getWeightCategory(
                data
            )
            val strFormat = String.format("%.2f", weightResult)
            binding.tvCategoryResult.text = categoryResult
            binding.tvValueHeight.text = height.toInt().toString()
            binding.tvValueWeight.text = weight.toInt().toString()
            binding.tvValueBmiResult.text = strFormat
            binding.tvType.text = type
            binding.tvResultMotivation.text = motivationResult
        }
    }

    fun getWeightCategory(data: WeightProperties?): ResultProperties {
        data?.let {
            val weightResult = it.weight / ((it.height / 100.0) * (it.height / 100.0))
            val type = it.type
            val categoryResult = getBMICategory(weightResult)
            val motivationResult = getMotivationResult(weightResult)
            return ResultProperties(
                type,
                data.height,
                data.weight,
                weightResult,
                categoryResult,
                motivationResult
            )
        }
        return ResultProperties(
            "Tidak ditemukan",
            0.0,
            0.0,
            0.0,
            "Tidak ditemukan ",
            "Tidak ditemukan"
        )
    }

    fun getMotivationResult(data: Double): String {
        val mtvResult = when {
            data < 18.5 -> "Jangan lupa makan makanan bergizi dan perbanyak asupan protein untuk mencapai berat badan idealmu!"
            data in 18.5..24.9 -> "Pertahankan gaya hidup sehatmu! Tetap aktif dan jaga pola makan seimbang."
            data in 25.0..29.9 -> "Yuk, mulai rutinitas olahraga dan kurangi konsumsi makanan tinggi kalori untuk hidup lebih sehat!"
            data > 30 -> "Mari menuju berat ideal, perubahan kecil setiap hari bisa membawa hasil besar. Konsultasikan dengan ahli untuk solusi terbaik!"
            else -> "Tidak ditemukan hasil. Coba cek kembali data yang dimasukkan."
        }
        return mtvResult
    }

    fun getBMICategory(weightResult: Double): String {
        val resultCategory = when {
            weightResult < 18.5 -> "Berat Badan Kurang"
            weightResult in 18.5..24.9 -> "Berat Badan Normal"
            weightResult in 25.0..29.9 -> "Berat Badan Berlebih"
            weightResult > 30 -> "Obesitas"
            else -> "Not found"
        }
        return resultCategory
    }

}
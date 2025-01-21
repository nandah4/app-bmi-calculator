package com.example.bmicalculator.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

            val (type, categoryResult, weightResult) = getWeightCategory(data)
            val strFormat = String.format("%.2f", weightResult)
            binding.tvHeight.text = categoryResult
            binding.tvWeight.text = strFormat
            binding.tvType.text = type
        }
    }

    fun getWeightCategory(data: WeightProperties?): Triple<String, String, Double> {
        data?.let {
            val weightResult = it.weight / ((it.height / 100.0) * (it.height / 100.0))
            val type = it.type
            val categoryResult = getBMICategory(weightResult)
            return Triple(type, categoryResult, weightResult)
        }
        return Triple("Unknow", "Unknown", 0.00)
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
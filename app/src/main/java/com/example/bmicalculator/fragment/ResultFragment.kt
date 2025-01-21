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

            data?.let { (type, weight, height) ->
                binding.tvType.text = type
                binding.tvHeight.text = height.toString()
                binding.tvWeight.text = weight.toString()
            }
        }
    }

}
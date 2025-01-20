package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.FragmentManBinding

class ManFragment : Fragment() {
    private lateinit var binding: FragmentManBinding
    private var onSubmitListener: OnSubmitListener? = null

    interface OnSubmitListener {
        fun OnSubmit(number: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.btnSubmitMan.setOnClickListener {
//            val heightValue = binding.edtHeightMain.text.toString().trim()
//            if (heightValue.isEmpty()) {
//                binding.edtHeightMain.error = "Value is empty"
//                return@setOnClickListener
//            }
//
//            onSubmitListener?.OnSubmit(heightValue)
//            binding.tvManHeight.text = heightValue
//        }
    }

    fun setOnSubmitListener(listener: OnSubmitListener) {
        onSubmitListener = listener
    }
}
package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bmicalculator.databinding.FragmentFormBinding

class FormFragment : DialogFragment() {
    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSubmitFormName.setOnClickListener {
            val nameValue = binding.edtFormName.editText?.text.toString().trim()
            if (nameValue.length < 1) {
                binding.edtFormName.error = "Character less than 1"
                return@setOnClickListener
            } else {
                Toast.makeText(this.requireActivity(), nameValue, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
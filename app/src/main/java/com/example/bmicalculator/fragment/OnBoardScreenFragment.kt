package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.databinding.FragmentOnBoardScreenBinding

class OnBoardScreenFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardScreenBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentManager = childFragmentManager
        val formFragment = FormFragment()

        binding.btnNextMain.setOnClickListener {
            formFragment.show(fragmentManager, FormFragment()::class.java.simpleName)
        }
    }
}

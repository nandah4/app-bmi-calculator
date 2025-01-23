package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.bmicalculator.R
import com.example.bmicalculator.data.WeightProperties
import com.example.bmicalculator.databinding.FragmentWomanBinding


class WomanFragment : Fragment() {

    private lateinit var binding: FragmentWomanBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWomanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mFragmentManager = parentFragmentManager

//        val replacePageFragment: (Fragment, String) -> Unit = { fragment, tag ->
//            val transaction = mFragmentManager.beginTransaction()
//            val isExistingFragment = mFragmentManager.findFragmentByTag(tag)
//
//            if (isExistingFragment != null) {
//                transaction.detach(isExistingFragment).attach(isExistingFragment)
//            } else {
//                transaction.apply {
//                    setReorderingAllowed(true)
//                    replace(R.id.container_fragment_home, fragment, tag)
//                    addToBackStack(null)
//                }
//            }
//            transaction.commit()
//        }
        val fragmentMan = ManFragment()
        binding.btnNextMan.setOnClickListener {
            mFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(
                    R.id.container_fragment_home,
                    fragmentMan,
                    ManFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }

        /*
        TODO: HANDLE INPUT FROM EDIT TEXT
         */
        binding.btnSubmitMan.setOnClickListener {
            val valueHeight = binding.edtWomanHeight.editText?.text.toString().trim()
            val valueWeight = binding.edtWomanWeight.editText?.text.toString().trim()

            if (valueHeight.isEmpty()) {
                binding.edtWomanHeight.error = "Must have at least 1 character"
                return@setOnClickListener
            }
            if (valueWeight.isEmpty()) {
                binding.edtWomanWeight.error = "Must have at least 1 character"
                return@setOnClickListener
            }

            val fragmentResult = ResultFragment()
            val bundle = Bundle()
            val data = WeightProperties("Man", valueWeight.toDouble(), valueHeight.toDouble())
            bundle.putParcelable(ResultFragment.EXTRA_WEIGHT_PROPERTIES, data)
            fragmentResult.arguments = bundle
            mFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(
                    R.id.container_fragment_home,
                    fragmentResult,
                    ResultFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
//            replacePageFragment(fragmentResult, ResultFragment::class.java.simpleName)
        }

        binding.edtWomanHeightInner.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.edtWomanHeight.helperText = "Maksimal 3 nomor"
            } else {
                binding.edtWomanHeight.helperText = null
            }
        }

        binding.edtWomanWeightInner.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.edtWomanWeight.helperText = "Maksimal 3 nomor"
            } else {
                binding.edtWomanWeight.helperText = null
            }
        }
    }
}
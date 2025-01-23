package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.R
import com.example.bmicalculator.data.WeightProperties
import com.example.bmicalculator.databinding.FragmentManBinding

class ManFragment : Fragment() {
    private lateinit var binding: FragmentManBinding
//    private var onCountListener: OnCountListener? = null
//
//
//    interface OnCountListener {
//        fun onSubmitData(data: WeightProperties)
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        if (context is OnCountListener) {
//            onCountListener = context
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*
        TODO: NAVIGASI KE FRAGMENT MAN / WOMAN
         */
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
        val fragmentWoman = WomanFragment()
        binding.btnNextWoman.setOnClickListener {
            mFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(
                    R.id.container_fragment_home,
                    fragmentWoman,
                    WomanFragment::class.java.simpleName
                )
                addToBackStack(null)
                commit()
            }
        }

        /*
        TODO: HANDLE INPUT FROM EDIT TEXT
         */
        binding.btnSubmitMan.setOnClickListener {
            val valueHeight = binding.edtManHeight.editText?.text.toString().trim()
            val valueWeight = binding.edtManWeight.editText?.text.toString().trim()

            if (valueHeight.isEmpty()) {
                binding.edtManHeight.error = "Must have at least 1 character"
                return@setOnClickListener
            }
            if (valueWeight.isEmpty()) {
                binding.edtManWeight.error = "Must have at least 1 character"
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
        }

        binding.edtManHeightInner.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.edtManHeight.helperText = "Maksimal 3 nomor"
            } else {
                binding.edtManHeight.helperText = null
            }
        }

        binding.edtManWeightInner.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.edtManWeight.helperText = "Maksimal 3 nomor"
            } else {
                binding.edtManWeight.helperText = null
            }
        }


    }
}
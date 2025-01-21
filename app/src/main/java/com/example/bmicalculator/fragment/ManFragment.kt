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

        val replacePageFragment: (Fragment, String) -> Unit = { fragment, tag ->
            val isExistingFragment = mFragmentManager.findFragmentByTag(tag)

            if (isExistingFragment !is Fragment) {
                mFragmentManager.beginTransaction().apply {
                    setReorderingAllowed(true)
                    replace(R.id.container_fragment_home, fragment, tag)
                    addToBackStack(null)
                    commit()
                }
            }
        }
        val fragmentMan = ManFragment()
        val fragmentWoman = WomanFragment()
        binding.btnNextMan.setOnClickListener {
            replacePageFragment(fragmentMan, ManFragment::class.java.simpleName)
        }
        binding.btnNextWoman.setOnClickListener {
            replacePageFragment(fragmentWoman, WomanFragment::class.java.simpleName)
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
            replacePageFragment(fragmentResult, ResultFragment::class.java.simpleName)
        }
    }
}
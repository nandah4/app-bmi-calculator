package com.example.bmicalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmicalculator.R
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
        binding.btnNextMan.setOnClickListener {
            val fragmentMan = ManFragment()
            replacePageFragment(fragmentMan, ManFragment::class.java.simpleName)
        }
        binding.btnNextWoman.setOnClickListener {
            val fragmentWoman = WomanFragment()
            replacePageFragment(fragmentWoman, WomanFragment::class.java.simpleName)
        }



        fun setOnSubmitListener(listener: OnSubmitListener) {
            onSubmitListener = listener
        }
    }
}
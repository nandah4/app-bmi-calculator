package com.example.bmicalculator.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bmicalculator.databinding.FragmentFormBinding

class FormFragment : DialogFragment() {
    private lateinit var binding: FragmentFormBinding
    private var listener: OnSubmitListener? = null

    interface OnSubmitListener {
        fun onSubmit(name: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSubmitListener) {
            listener = context
        } else {
            throw ClassCastException("$context mus implemented OnSubmitListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitFormName.setOnClickListener {
            val nameValue = binding.edtFormName.editText?.text.toString().trim()
            if (nameValue.isEmpty()) {
                binding.edtFormName.error = "Character less than 1"
                return@setOnClickListener
            } else {
                listener?.onSubmit(nameValue)
                dismiss()
            }
//                val intent = Intent(requireActivity(), HomeActivity::class.java)
//                intent.putExtra(HomeActivity.EXTRA_NAME, nameValue)
//                startActivity(intent)
//                requireActivity().finish()


        }
    }
}
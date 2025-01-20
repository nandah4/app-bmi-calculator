package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityHomeBinding
import com.example.bmicalculator.fragment.FormFragment
import com.example.bmicalculator.fragment.ManFragment
import com.example.bmicalculator.fragment.WomanFragment


class HomeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val formFragment = intent.getStringExtra(EXTRA_NAME)
        binding.tvName.text = formFragment
        
        val mFragmentManager = supportFragmentManager
        val fragmentMan = ManFragment()
        val fragmentWoman = WomanFragment()

        mFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.container_fragment_home, fragmentMan, ManFragment::class.java.simpleName)
            .commit()

        binding.btnMan.setOnClickListener {
            val isFragmentExist =
                mFragmentManager.findFragmentByTag(ManFragment::class.java.simpleName)

            if (isFragmentExist == null) {
                mFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(
                        R.id.container_fragment_home,
                        fragmentMan,
                        ManFragment::class.java.simpleName
                    )
                    .commit()
            }
        }

        binding.btnWoman.setOnClickListener {
            val isWFragmentExist =
                mFragmentManager.findFragmentByTag(WomanFragment::class.java.simpleName)

            mFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(
                    R.id.container_fragment_home,
                    fragmentWoman,
                    WomanFragment::class.java.simpleName
                )
                .commit()
        }
    }
}
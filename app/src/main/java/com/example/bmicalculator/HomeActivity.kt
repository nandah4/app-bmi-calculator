package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
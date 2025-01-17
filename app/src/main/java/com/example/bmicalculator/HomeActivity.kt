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



        mFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.container_fragment_home, fragmentMan, ManFragment::class.java.simpleName)
            .commit()

        binding.btnMan.setOnClickListener {
            mFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.container_fragment_home, fragmentMan, ManFragment::class.java.simpleName)
                .commit()
        }
    }
}
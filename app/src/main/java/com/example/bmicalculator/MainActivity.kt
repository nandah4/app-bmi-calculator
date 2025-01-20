package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import com.example.bmicalculator.fragment.FormFragment
import com.example.bmicalculator.fragment.OnBoardScreenFragment

class MainActivity : AppCompatActivity(), FormFragment.OnSubmitListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment1 = OnBoardScreenFragment()

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.fragment_container, fragment1, OnBoardScreenFragment::class.java.simpleName)
            .commit()
    }

    override fun onSubmit(name: String) {
        val intent = Intent(this@MainActivity, HomeActivity::class.java)
        intent.putExtra(HomeActivity.EXTRA_NAME, name)
        startActivity(intent)

        finish()
    }
}
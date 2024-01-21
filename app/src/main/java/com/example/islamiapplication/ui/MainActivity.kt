package com.example.islamiapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.islamiapplication.R
import com.example.islamiapplication.databinding.ActivityMainBinding
import com.example.islamiapplication.ui.fragments.HadeethFragment
import com.example.islamiapplication.ui.fragments.QuranFragment
import com.example.islamiapplication.ui.fragments.RadioFragment
import com.example.islamiapplication.ui.fragments.TasbeehFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.quran_menu -> {
                    pushFragment(QuranFragment())
                }

                R.id.hadeeth_menu -> {
                    pushFragment(HadeethFragment())
                }

                R.id.tasbeeh_menu -> {
                    pushFragment(TasbeehFragment())
                }

                R.id.radio_menu -> {
                    pushFragment(RadioFragment())
                }
            }

            return@setOnItemSelectedListener true
        }
        binding.bottomNavView.selectedItemId = R.id.quran_menu
    }


    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}

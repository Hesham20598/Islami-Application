package com.example.islamiapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islamiapplication.R
import com.example.islamiapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
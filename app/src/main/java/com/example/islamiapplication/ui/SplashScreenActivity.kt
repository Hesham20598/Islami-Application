package com.example.islamiapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.islamiapplication.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val DELAY_TIMER:Long = 2000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(mainLooper).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        },DELAY_TIMER)
    }
}
package com.example.islamiapplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createChannels()

    }

    companion object {
        val RADIO_CHANNEL_ID = "radios_channel"
    }

    private fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val mChannel = NotificationChannel(RADIO_CHANNEL_ID, name, importance)
            mChannel.lightColor = Color.CYAN
            mChannel.description = description
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }
}
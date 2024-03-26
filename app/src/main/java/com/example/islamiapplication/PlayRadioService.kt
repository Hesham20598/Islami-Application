package com.example.islamiapplication

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.islamiapplication.model.RadiosItem

class PlayRadioService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }


    inner class MyBinder : Binder() {
        fun getService(): PlayRadioService {
            return this@PlayRadioService
        }
    }

    var mediaPlayer = MediaPlayer()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val urlToPlay = intent?.getStringExtra("url")
        val name = intent?.getStringExtra("name")
        val id = intent?.getIntExtra("id", 0)
        val action = intent?.getStringExtra("action")
        if (urlToPlay != null && name != null && id != null) {
            startMediaPlayer(urlToPlay, name, id)
        }
        when (action) {
            Play_Action, Pause_Action -> PlayPauseMediaPlayer()
            Stop_Action -> stopMediaPlayer()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    fun PlayPauseMediaPlayer() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        } else mediaPlayer.pause()
        updateNotification()
    }

    private fun updateNotification() {
        val notificationView = RemoteViews(packageName, R.layout.notification_view)
        notificationView.setTextViewText(R.id.title, "Islami App Radio")
        notificationView.setTextViewText(R.id.description, name)
        notificationView.setImageViewResource(
            R.id.play,
            if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
        notificationView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())

        val notification = NotificationCompat.Builder(this, MyApplication.RADIO_CHANNEL_ID)
            .setSound(null)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.moshaf_gold2)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(notificationView)
            .build()
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    fun getIsPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    private fun stopMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        stopForeground(true)
        stopSelf()
    }

    var name = ""
    var id = 0
     fun startMediaPlayer(urlToPlay: String, name: String, id: Int) {

        pauseMediaPlayer()

        if (this.name == name) {
            PlayPauseMediaPlayer()
        } else {
            this.name = name
            this.id = id
            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
                createNotificationForMediaPlayer(name)

            }

        }
    }

    private fun pauseMediaPlayer() {
        if (mediaPlayer.isPlaying) mediaPlayer.pause()

    }

    private fun createNotificationForMediaPlayer(name: String?) {

        val notificationView = RemoteViews(packageName, R.layout.notification_view)
        notificationView.setTextViewText(R.id.title, "Islami App Radio")
        notificationView.setTextViewText(R.id.description, name)
        notificationView.setImageViewResource(
            R.id.play,
            if (mediaPlayer.isPlaying) R.drawable.ic_pause else R.drawable.ic_play
        )
        notificationView.setOnClickPendingIntent(R.id.play, getPlayButtonPendingIntent())

        val notification = NotificationCompat.Builder(this, MyApplication.RADIO_CHANNEL_ID)
            .setSound(null)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.drawable.moshaf_gold2)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(notificationView)
            .build()
        startForeground(1, notification)


    }

    val Play_Action = "play"
    val Pause_Action = "pause"
    val Stop_Action = "stop"
    private fun getPlayButtonPendingIntent(): PendingIntent {
        val intent = Intent(this, PlayRadioService::class.java)
        if (mediaPlayer.isPlaying)
            intent.putExtra("action", Pause_Action)
        else
            intent.putExtra("action", Play_Action)
        return PendingIntent.getService(this, 12, intent, PendingIntent.FLAG_IMMUTABLE)
    }




}
package com.example.donation.NotificationScheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.donation.R

class MyNotificationReceiver : BroadcastReceiver() {
    companion object {
        private const val PREFS_NAME = "NotificationPrefs"
        private const val COUNTER_KEY = "notificationCounter"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val counter = preferences.getInt(COUNTER_KEY, 0)
//        preferences.edit().clear()

        if (counter < 12) {
            val showNotification = preferences.getBoolean("notificationEnabled", true)

            if (showNotification) {
                val CHANNEL_ID = "MyAppChannel"
                val CHANNEL_NAME = "My App Notifications"
                val CHANNEL_DESCRIPTION = "Notifications for My App"
                val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle("Save Our Elephant")
                    .setContentText("A monthly donation of RM 300 has been made")
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setAutoCancel(true)

                // Create a notification manager
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // Create a notification channel (required for Android Oreo and above)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply {
                        description = CHANNEL_DESCRIPTION
                        enableLights(true)
                        lightColor = Color.GREEN
                    }
                    notificationManager.createNotificationChannel(channel)
                }

                // Show the notification
                notificationManager.notify(123, notificationBuilder.build())

                val newCounter = counter + 1

                preferences.edit().apply {
                    putInt(COUNTER_KEY, newCounter)
                    apply()
                }
            }
        } else {
            // Clear the preferences when the counter reaches 12
            preferences.edit().clear().apply()
        }
    }
}

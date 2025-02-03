package com.smialko.reminderhabitstracker.presentation.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.smialko.reminderhabitstracker.MainActivity
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.utils.Constants.CHANNEL_ID
import com.smialko.reminderhabitstracker.utils.Constants.CHANNEL_NAME

object Notification {

    fun showNotification(context: Context, textReminder: String) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Notification")
            .setContentText(textReminder)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setVibrate(longArrayOf(100, 200, 300, 400))
            .build()
        notificationManager.notify(1, notification)
    }
}

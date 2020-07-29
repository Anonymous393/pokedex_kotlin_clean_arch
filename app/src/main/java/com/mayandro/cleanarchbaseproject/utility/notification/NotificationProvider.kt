package com.mayandro.cleanarchbaseproject.utility.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.ui.home.MainActivity
import timber.log.Timber

class NotificationProvider(private val context: Context) {

    companion object{
        private val NOTIFICATION_CHANNEL = "pokemon_channel"
        val FETCH_POKEMON_NOTIFICATION_ID = 1002
    }

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun getNotificationBuilder(
        title: String,
        initializationMessage: String,
        channelId: String = NOTIFICATION_CHANNEL,
        pendingIntent: PendingIntent? = null
    ): NotificationCompat.Builder {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId, channelId,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.vibrationPattern = longArrayOf(0L)
            notificationChannel.enableVibration(false)
            notificationChannel.setSound(null, null)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setContentTitle(title)
        notificationBuilder.setContentText(initializationMessage)
        notificationBuilder.setAutoCancel(false
        )
        notificationBuilder.setSmallIcon(R.drawable.pokeball)
        notificationBuilder.setDefaults(0)
        notificationBuilder.setSound(null)
        notificationBuilder.color = ContextCompat.getColor(context, R.color.colorPrimary)
        notificationBuilder.setChannelId(channelId)
        pendingIntent?.let { intent ->
            notificationBuilder.setContentIntent(intent)
        }
        return notificationBuilder
    }

    fun removeNotification(
        notificationId: Int
    ) {
        notificationManager.cancel(notificationId)
    }

    fun clearAllNotifications() {
        notificationManager.cancelAll()
    }

    fun updateNotificationMessage(
        message: String,
        notificationId: Int = FETCH_POKEMON_NOTIFICATION_ID,
        notificationBuilder: NotificationCompat.Builder?
    ) {
        notificationBuilder?.let {
            it.setContentText(message)
            notificationManager.notify(notificationId, it.build())
        } ?: kotlin.run {
            Timber.w("NotificationProvider.updateNotificationMessage updateProgress")
        }
    }

    fun updateProgress(
        progressPercentage: Int,
        notificationId: Int = FETCH_POKEMON_NOTIFICATION_ID,
        message: String = "",
        notificationBuilder: NotificationCompat.Builder?
    ) {
        notificationBuilder?.let {
            it.setProgress(100, progressPercentage, false)
            it.setDefaults(0)
            if (message.isNotBlank()) {
                it.setContentText(message)
            }
            notificationManager.notify(notificationId, it.build())
        } ?: kotlin.run {
            Timber.w("NotificationProvider.showIndefiniteProgress updateProgress")
        }
    }

    fun showNotification(
        notificationId: Int = FETCH_POKEMON_NOTIFICATION_ID,
        notificationBuilder: NotificationCompat.Builder?
    ) {
        notificationBuilder?.let {
            notificationManager.notify(notificationId, it.build())
        } ?: kotlin.run {
            Timber.w("NotificationProvider.showNotification nullpointerexception $notificationId")
        }
    }

    fun showProcessCompletionMessage(
        completionMessage: String,
        notificationId: Int = FETCH_POKEMON_NOTIFICATION_ID,
        pendingIntent: PendingIntent? = null,
        notificationBuilder: NotificationCompat.Builder?
    ) {
        notificationBuilder?.let {
            it.setContentText(completionMessage)
            it.setAutoCancel(true)
            it.setOngoing(false)
            it.setProgress(0, 0, false)

            pendingIntent?.let { intent ->
                notificationBuilder.setContentIntent(intent)
            }
            notificationManager.notify(notificationId, it.build())
        } ?: kotlin.run {
            Timber.w("NotificationProvider.showProcessCompletionMessage nullpointerexception")
        }
    }

    fun generatePendingIntent(): PendingIntent {
        val resultIntent =  Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        return PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT)
    }
}
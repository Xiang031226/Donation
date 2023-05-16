import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.donation.R

object NotificationUtils {

    private const val CHANNEL_ID = "MyAppChannel"
    private const val CHANNEL_NAME = "My App Notifications"
    private const val CHANNEL_DESCRIPTION = "Notifications for My App"

    fun showNotification(context: Context, title: String, message: String) {
        // Create a notification builder
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
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
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    // Define a constant for the notification ID
    private const val NOTIFICATION_ID = 123
}

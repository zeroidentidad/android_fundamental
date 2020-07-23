package com.linkedin.curso.android.mitiempo.mitiempo.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.login.LoginActivity


class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        L.d("From: " + remoteMessage.from)
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            L.d("Message data payload: " + remoteMessage.data)
        }
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            L.d("Message Notification Body: " + remoteMessage.notification!!.body)
            crearMiNotificacion(remoteMessage.notification!!.body)
        }
    }

    private fun crearMiNotificacion(mensaje: String?) {

        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mBuilder = NotificationCompat.Builder(this, getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_notificar)
                .setContentTitle(getString(R.string.notificaciones_titulo))
                .setContentText(mensaje)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        crearNotificationChannel()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(12345, mBuilder.build())
    }

    private fun crearNotificationChannel() {
        // Crear un canal de noticaciones solo es necesario a partir del API 26+
        // ya que solo está disponible a partir de este sdk
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nombre = getString(R.string.notif_channel_name)
            val descripcion = getString(R.string.notif_channel_desc)
            val importancia = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.app_name), nombre, importancia)
            channel.description = descripcion
            // Registra el canal con el sistema; no se puede cambiar la importancia u
            // otros comportamientos de la notificación después de definir esto
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}

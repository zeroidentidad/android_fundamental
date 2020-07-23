package com.linkedin.curso.android.mitiempo.mitiempo.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.utils.Dialogo

class SMSBrodcastReceiver : BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras
        val pdus = bundle?.get("pdus") as Array<*>
        val mensajes = arrayOfNulls<SmsMessage>(pdus.size)
        val format = bundle.getString("format")

        for (i in mensajes.indices) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mensajes[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
            } else {
                mensajes[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
            }

            val idMensaje = mensajes[i]?.originatingAddress
            val textoMensaje = mensajes[i]?.messageBody

            context?.let { Dialogo.miToast(it, context.getString(R.string.sms_recibido) + " $idMensaje : $textoMensaje") }

        }
    }
}
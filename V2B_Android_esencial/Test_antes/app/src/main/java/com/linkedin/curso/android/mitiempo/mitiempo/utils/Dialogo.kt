package com.linkedin.curso.android.mitiempo.mitiempo.utils

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.linkedin.curso.android.mitiempo.mitiempo.R
import java.util.*


class Dialogo {
    companion object {
        fun miToast(context: Context, message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
            Toast.makeText(context, message, duration).show()
        }

        fun miSnackBar(view: View, mensaje: String, accion: Boolean, tiempo: Int = if (accion) Snackbar.LENGTH_LONG else Snackbar.LENGTH_INDEFINITE): Snackbar {
            return Snackbar.make(view, mensaje, tiempo).setActionTextColor(Color.RED)
        }

        fun crearDialogoSimple(context: Context, titulo: String?, mensaje: String?,
                               positivo: DialogInterface.OnClickListener?,
                               negativo: DialogInterface.OnClickListener?,
                               neutro: DialogInterface.OnClickListener?) {
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(false)
            if (titulo?.isNotEmpty()!!)
                builder.setTitle(titulo)
            if (mensaje?.isNotEmpty()!!)
                builder.setMessage(mensaje)
            if (positivo != null)
                builder.setPositiveButton(context.resources.getString(R.string.boton_aceptar), positivo)
            if (negativo != null)
                builder.setNegativeButton(context.resources.getString(R.string.boton_cancelar), negativo)
            if (neutro != null)
                builder.setNeutralButton(context.resources.getString(R.string.boton_neutro), neutro)
            builder.show()
        }

        fun crearDialogoFecha(context: Context) {
            val calendario = Calendar.getInstance()
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)
            val anio = calendario.get(Calendar.YEAR)
            val recogerFecha = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val mesActual = month + 1
                val diaFormateado = if (dayOfMonth < 10) "0" + dayOfMonth.toString() else dayOfMonth.toString()
                val mesFormateado = if (mesActual < 10) "0" + mesActual.toString() else mesActual.toString()
                miToast(context, StringBuilder("$diaFormateado/$mesFormateado/$year"))
            }, anio, mes, dia)
            recogerFecha.show()
        }

        fun crearDialogoHora(context: Context) {
            val calendario = Calendar.getInstance()
            val hora = calendario.get(Calendar.HOUR_OF_DAY)
            val minuto = calendario.get(Calendar.MINUTE)
            val recogerHora = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val horaFormateada = if (hourOfDay < 10) StringBuilder("0$hourOfDay") else hourOfDay.toString()
                val minutoFormateado = if (minute < 10) StringBuilder("0$minute") else minute.toString()
                val AM_PM = if (hourOfDay < 12) {
                    "a.m."
                } else {
                    "p.m."
                }
                miToast(context, "$horaFormateada:$minutoFormateado $AM_PM")
            }, hora, minuto, false)
            recogerHora.show()
        }
    }
}

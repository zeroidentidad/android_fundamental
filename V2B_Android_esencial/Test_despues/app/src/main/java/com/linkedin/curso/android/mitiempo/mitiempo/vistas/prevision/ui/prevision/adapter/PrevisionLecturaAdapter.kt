package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.utils.Dialogo

class PrevisionLecturaAdapter(val prevision: List<ModelosWeather.Lista>?) : RecyclerView.Adapter<PrevisionLecturaAdapter.TemperaturaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TemperaturaHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_prevision_lectura, parent, false))

    override fun getItemCount() = prevision?.size ?: 0

    override fun onBindViewHolder(holder: TemperaturaHolder, position: Int) {
        holder.bind(prevision!![position])
    }

    class TemperaturaHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var mContainer: LinearLayout = v.findViewById(R.id.adapter_prevision_lectura_container)
        private var mFecha: TextView = v.findViewById(R.id.adapter_prevision_lectura_fecha_tv)
        private var mMax: TextView = v.findViewById(R.id.adapter_prevision_lectura_max_tv)
        private var mMin: TextView = v.findViewById(R.id.adapter_prevision_lectura_min_tv)

        fun bind(item: ModelosWeather.Lista) = with(itemView) {
            mFecha.text = item.dt_txt
            mMax.text = context.getString(R.string.temperatura, item.main.temp_max.toString())
            mMin.text = context.getString(R.string.temperatura, item.main.temp_min.toString())
            mContainer.setOnClickListener { Dialogo.miToast(context, "${context.getString(R.string.pression)}: ${item.main.pressure}") }
        }
    }
}

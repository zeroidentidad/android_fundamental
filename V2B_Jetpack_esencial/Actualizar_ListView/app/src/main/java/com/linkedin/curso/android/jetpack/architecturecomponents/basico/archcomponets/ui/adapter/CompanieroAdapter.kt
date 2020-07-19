package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.data.database.CompanieroEntity

class CompanieroAdapter(val companieros: List<CompanieroEntity>?) : RecyclerView.Adapter<CompanieroAdapter.CompanieroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CompanieroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.architecture_comp_compi_view, parent, false))

    override fun getItemCount() = companieros?.size ?: 0

    override fun onBindViewHolder(holder: CompanieroViewHolder, position: Int) {
        holder.bind(companieros!![position])
    }

    class CompanieroViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var TAG = "CompanieroViewHolder"
        private var mNombre: TextView = v.findViewById(R.id.architecture_comp_compi_view_nombre)
        private var mCompania: TextView = v.findViewById(R.id.architecture_comp_compi_view_compania)
        private var mEmail: TextView = v.findViewById(R.id.architecture_comp_compi_view_email)
        private var mTelf: TextView = v.findViewById(R.id.architecture_comp_compi_view_telf)
        private var mContainer: LinearLayout = v.findViewById(R.id.architecture_comp_compi_view_contenedor)

        fun bind(item: CompanieroEntity) = with(itemView) {
            mNombre.text = item.nombre
            mCompania.text = item.compania
            mEmail.text = item.email
            mTelf.text = item.telf
            mContainer.setOnClickListener {
                val mensaje = "${item.nombre}:: ${item.compania}"
                Log.d(TAG, mensaje)
            }
        }
    }
}
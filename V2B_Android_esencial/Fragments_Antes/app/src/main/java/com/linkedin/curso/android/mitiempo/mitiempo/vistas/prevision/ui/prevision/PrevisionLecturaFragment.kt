package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.linkedin.curso.android.mitiempo.mitiempo.R

class PrevisionLecturaFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v?.id == mGraficoBT.id) {
            // TODO:: Avisamos al Listener para ir al grafico
        }
    }

    // TODO:: crear el método para poder crear nuevas instancias del Fragment
    companion object;

    private lateinit var lecturaViewModel: PrevisionLecturaViewModel
    private lateinit var mPrevisionTV: TextView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mGraficoBT: Button
    private lateinit var mListener: IrGraficoListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // TODO:: iniciar los diferentes elementos de la vista
        val view = inflater.inflate(R.layout.prevision_fragment, container, false)
        // TODO:: Generar un botón para cuando no estemos en tablet poder ir a ver el gráfico
        return view
    }

    override fun onResume() {
        super.onResume()
        // TODO:: Incluir una actualizacion para no perder los datos que tenemos en el lecturaViewModel
        actualizarUI()
    }

    // TODO:: Cuando los datos hayan sido traidos del servidor se ejecutará este código
    fun recogerDatos() {
        // TODO:: cargar los datos en el lecturaViewModel para disponer de ellos en cualquier momento
        // TODO:: Actualizar la ui con los datos que tenemos del servidor
    }

    private fun actualizarUI() {
        mProgressBar.visibility = View.GONE
        val result = StringBuilder()
        // TODO:: rellenar la interfaz con los datos que tenemos disponibles
    }

    // TODO:: Crear una interfaz para poder implementar el gráfico
    interface IrGraficoListener

    // TODO:: Funcion que setea el listener al que hay que avisar
    fun setIrGraficoListener(listener: IrGraficoListener) {
    }

    fun ocultarBotonGrafico() {
        mGraficoBT.visibility = View.GONE
    }

}

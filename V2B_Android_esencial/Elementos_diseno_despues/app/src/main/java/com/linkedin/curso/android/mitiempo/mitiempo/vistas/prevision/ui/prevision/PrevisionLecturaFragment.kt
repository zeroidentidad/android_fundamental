package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather

class PrevisionLecturaFragment : Fragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v?.id == mGraficoBT.id) {
            this.mListener.onIrGraficoSeleccionado()
        }
    }

    companion object {
        fun newInstance() = PrevisionLecturaFragment()
    }

    private lateinit var lecturaViewModel: PrevisionLecturaViewModel
    private lateinit var mPrevisionTV: TextView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mGraficoBT: Button
    private lateinit var mListener: IrGraficoListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.prevision_fragment, container, false)
        mPrevisionTV = view.findViewById(R.id.fragment_prevision_prevision_tv)
        mProgressBar = view.findViewById(R.id.fragment_prevision_progress_bar)
        mProgressBar.visibility = View.VISIBLE
        mGraficoBT = view.findViewById(R.id.fragment_prevision_prevision_grafico_bt)
        mGraficoBT.setOnClickListener(this)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lecturaViewModel = ViewModelProviders.of(this).get(PrevisionLecturaViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        actualizarUI()
    }

    fun recogerDatos(prevision: ModelosWeather.Prevision?) {
        lecturaViewModel.prevision = prevision
        actualizarUI()
    }

    private fun actualizarUI() {
        mProgressBar.visibility = View.GONE
        val result = StringBuilder()
        lecturaViewModel.prevision?.list?.forEach {
            result.append(it.dt_txt)
                    .append(":: ")
                    .append(it.main.temp_max)
                    .append(" Maxima ")
                    .append(it.main.temp_min)
                    .append(" Minima \n")
        }
        mPrevisionTV.text = result
    }

    interface IrGraficoListener {
        fun onIrGraficoSeleccionado()
    }

    fun setIrGraficoListener(listener: IrGraficoListener) {
        this.mListener = listener
    }

    fun ocultarBotonGrafico() {
        mGraficoBT.visibility = View.GONE
    }

}

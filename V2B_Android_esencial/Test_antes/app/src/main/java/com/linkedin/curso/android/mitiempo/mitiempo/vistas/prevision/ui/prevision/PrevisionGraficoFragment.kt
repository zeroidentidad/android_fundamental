package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather


class PrevisionGraficoFragment : Fragment() {

    private lateinit var mGraficoLineal: LineChart
    private lateinit var dataSet: LineDataSet

    companion object {
        fun newInstance() = PrevisionGraficoFragment()
    }

    private lateinit var viewModel: PrevisionGraficoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.prevision_grafico_fragment, container, false)
        mGraficoLineal = view.findViewById(R.id.fragment_grafico_prevision_lv) as LineChart
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(PrevisionGraficoViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        generarVistaGrafico()
    }

    fun iniciarDatos(prevision: ModelosWeather.Prevision?) {
        viewModel.generarDatosGrafico(prevision)
        generarVistaGrafico()
    }

    @VisibleForTesting
    fun generarVistaGrafico() {
        if (viewModel.mDatosGrafico.count() > 0) {
            dataSet = LineDataSet(viewModel.mDatosGrafico, "Temperaturas máximas")
            mGraficoLineal.data = LineData(dataSet)
            mGraficoLineal.invalidate()
        }
    }

}

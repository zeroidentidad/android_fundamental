package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.PrevisionActivity
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.adapter.PrevisionLecturaAdapter

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
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mGraficoBT: Button
    private lateinit var mListener: IrGraficoListener
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: PrevisionLecturaAdapter
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.prevision_fragment, container, false)
        mRecyclerView = view.findViewById(R.id.fragment_prevision_prevision_rv)
        initRecyclerView()
        mSwipeRefreshLayout = view.findViewById(R.id.fragment_prevision_swipe_rl)
        mSwipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this.context!!, R.color.primary_dark_material_light),
                ContextCompat.getColor(this.context!!, R.color.accent_material_light),
                ContextCompat.getColor(this.context!!, R.color.foreground_material_light)
        )
        mSwipeRefreshLayout.setOnRefreshListener {
            if (activity is PrevisionActivity) {
                (activity as PrevisionActivity).getDatosActividadPrevia()
            }
        }
        mProgressBar = view.findViewById(R.id.fragment_prevision_progress_bar)
        mProgressBar.visibility = View.VISIBLE
        mGraficoBT = view.findViewById(R.id.fragment_prevision_prevision_grafico_bt)
        mGraficoBT.setOnClickListener(this)
        return view
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = mLinearLayoutManager
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
        mSwipeRefreshLayout.isRefreshing = false
        lecturaViewModel.prevision = prevision
        actualizarUI()
    }

    private fun actualizarUI() {
        mProgressBar.visibility = View.GONE
        mAdapter = PrevisionLecturaAdapter(lecturaViewModel.prevision?.list)
        mRecyclerView.adapter = mAdapter
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

package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.ui.architecturecomp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.adapter.CompanieroAdapter
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.utils.InjectorUtils

class ArchitectureCompFragment : Fragment() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: CompanieroAdapter

    companion object {
        fun newInstance() = ArchitectureCompFragment()
    }

    private lateinit var viewModel: ArchitectureCompViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.architecture_comp_fragment, container, false)
        mRecyclerView = view.findViewById(R.id.architecture_comp_fragment_recyclerview)
        initRecyclerView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Declaramos el ViewModel
        val factory = InjectorUtils.inyectarArchComViewModelFactory(this.context!!)
        viewModel = ViewModelProviders.of(this, factory).get(ArchitectureCompViewModel::class.java)

        // Suscribirnos a los cambios de los datos
        suscribirnosCambios()
    }

    private fun suscribirnosCambios() {
        viewModel.mListaCompanieros.observe(this.activity!!, Observer { value ->
            mAdapter = CompanieroAdapter(value)
            mRecyclerView.adapter = mAdapter
        })
    }

    private fun initRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(this.activity)
        mRecyclerView.layoutManager = mLinearLayoutManager
    }


}

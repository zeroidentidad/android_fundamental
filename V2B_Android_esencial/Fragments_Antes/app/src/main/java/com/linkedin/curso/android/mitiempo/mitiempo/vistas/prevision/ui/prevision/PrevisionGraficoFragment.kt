package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linkedin.curso.android.mitiempo.mitiempo.R


class PrevisionGraficoFragment : Fragment() {

    // TODO:: variable en la que incluiremos los datos del gráfico
    // TODO:: variable donde incluiremos los datos del conjunto de datos

    // TODO:: Crearemos una nueva instancia para trabajar con ella


    // TODO:: Generar el nuevo ViewModel que se necesita para mantener los datos
    private lateinit var viewModel: PrevisionGraficoViewModel

    // TODO:: Cuando se crea la vista del Fragment, inicializaremos los elementos de la vista
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.prevision_grafico_fragment, container, false)
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this).get(PrevisionGraficoViewModel::class.java)
    }

    // TODO:: Función que se llamará cuando se tengan los datos de servidor para generar los datos
    fun iniciarDatos() {
        // TODO:: Llamar al viewModel para que se encarge de recoger los datos
        // TODO:: Una vez que ha creado el ViewModel los datos es necesario mostrarlos
    }

    @VisibleForTesting
    fun generarVistaGrafico() {
        // TODO:: El onResume se llamará antes de tener los datos, por lo que debemos prevenir errores de
        // TODO:: Crear un conjunto de datos con los datos generados por el ViewModel
        // TODO:: Cargar los datos dentro del gráfico
        // TODO:: refrescar los datos del gráfico
    }

}

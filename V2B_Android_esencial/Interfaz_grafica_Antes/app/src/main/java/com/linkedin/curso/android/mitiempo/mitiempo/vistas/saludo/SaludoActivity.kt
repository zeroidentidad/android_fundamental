package com.linkedin.curso.android.mitiempo.mitiempo.vistas.saludo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R

class SaludoActivity : BaseActivity(), View.OnClickListener {
    // TODO:: Definición de los diferentes componentes de la vista
    private lateinit var mSaludoTV: TextView
    private lateinit var mSaludoET: EditText
    private lateinit var mSaludoBT: Button
    private lateinit var mTiempoIV: ImageView

    override fun onClick(v: View?) {
        when (v?.id) {
        // TODO:: en el caso de que el click se haga en el botón
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)
        // TODO:: Crear la función para iniciar los elementos
    }

    override fun initViews() {
        // TODO: Iniciar los componentes
        // TODO: Iniciar los eventos de la vista
    }


}

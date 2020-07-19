package com.linkedin.curso.android.jetpack.architecturecomponents.basico.databinding

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.databinding.modelo.Usuario
import com.linkedin.curso.android.jetpack.architecturecomponents.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {

    private lateinit var mListaNombres: ArrayList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_data_binding)
        var binding = DataBindingUtil.setContentView<ActivityDataBindingBinding>(this, R.layout.activity_data_binding)

        val usuarioCodigo = Usuario("Pepito", "Grillo")

        binding.usuario = usuarioCodigo

        // Ejemplo listas en databinding
        mListaNombres = ArrayList()
        for (count in 1..5) {
            val usuario = Usuario("Pepito $count", "Grillo $count")
            mListaNombres.add(usuario)
        }
        binding.listaUsuario = mListaNombres

        // Ejemplo acceso a recursos
        val textoGrande = true
        binding.textoGrande = textoGrande
    }

}

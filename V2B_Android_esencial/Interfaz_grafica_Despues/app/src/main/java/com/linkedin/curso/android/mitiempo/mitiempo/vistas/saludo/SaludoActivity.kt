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
    private lateinit var mSaludoTV: TextView
    private lateinit var mSaludoET: EditText
    private lateinit var mSaludoBT: Button
    private lateinit var mTiempoIV: ImageView

    override fun onClick(v: View?) {
        when (v?.id) {
            mSaludoBT.id -> {
                mSaludoTV.text = mSaludoET.text.toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)
        initViews()
    }

    override fun initViews() {
        mSaludoTV = findViewById(R.id.activity_saludo_id_usuario)
        mSaludoET = findViewById(R.id.activity_saludo_nuevo_nombre_et)
        mSaludoBT = findViewById(R.id.activity_saludo_nuevo_nombre_bt)
        mTiempoIV = findViewById(R.id.activity_saludo_tiempo_image)
        mSaludoBT.setOnClickListener(this)
    }


}

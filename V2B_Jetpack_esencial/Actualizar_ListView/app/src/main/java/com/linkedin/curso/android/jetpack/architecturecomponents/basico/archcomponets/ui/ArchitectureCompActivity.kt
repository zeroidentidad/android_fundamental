package com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.linkedin.curso.android.jetpack.architecturecomponents.R
import com.linkedin.curso.android.jetpack.architecturecomponents.basico.archcomponets.ui.ui.architecturecomp.ArchitectureCompFragment

class ArchitectureCompActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.architecture_comp_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ArchitectureCompFragment.newInstance())
                    .commitNow()
        }
    }

}

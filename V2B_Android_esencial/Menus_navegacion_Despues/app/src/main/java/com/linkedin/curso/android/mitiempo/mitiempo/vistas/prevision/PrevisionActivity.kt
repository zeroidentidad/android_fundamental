package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.widget.FrameLayout
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.OpenWeatherServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ExtraNames
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.PrevisionGraficoFragment
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.PrevisionLecturaFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PrevisionActivity : BaseActivity(), PrevisionLecturaFragment.IrGraficoListener {
    private var mFrameLayout: FrameLayout? = null
    private var mFrameLayoutTablet: FrameLayout? = null

    private var mPrevisionLecturaFragment: PrevisionLecturaFragment? = null
    private var mPrevisionGraficoFragment: PrevisionGraficoFragment? = null

    private var disposable: Disposable? = null
    private val openWeatherServicio by lazy {
        OpenWeatherServicio.crear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prevision_activity)
        initViews()

        getDatosActividadPrevia()
        iniciarFragmentos(savedInstanceState)
    }

    override fun initViews() {
        mFrameLayout = findViewById(R.id.activity_prevision_fragment_container)
        mFrameLayoutTablet = findViewById(R.id.prevision_activity_tablet_fragment_grafico)
    }

    private fun iniciarFragmentos(savedInstanceState: Bundle?) {

        if (savedInstanceState == null && mFrameLayout != null) {
            mPrevisionLecturaFragment = PrevisionLecturaFragment.newInstance()
            mPrevisionLecturaFragment?.setIrGraficoListener(this)
            mPrevisionGraficoFragment = PrevisionGraficoFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.activity_prevision_fragment_container, mPrevisionLecturaFragment)
                    .commit()
            if (mFrameLayoutTablet != null) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.prevision_activity_tablet_fragment_grafico, mPrevisionGraficoFragment)
                        .addToBackStack(null)
                        .commit()
            } else {
                supportFragmentManager.beginTransaction().attach(mPrevisionGraficoFragment).commit()
            }
        }
    }

    override fun onIrGraficoSeleccionado() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_prevision_fragment_container, mPrevisionGraficoFragment)
                .addToBackStack(null)
                .commit()
    }

    fun getDatosActividadPrevia() {
        val presion: ModelosWeather.PosicionUsuario? = intent?.extras?.getParcelable(ExtraNames.POSICION_USUARIO_INFO)
        if (presion != null) {
            llamarServidorPrevision(presion)
        }
    }

    @VisibleForTesting
    fun previsioRecibida(prevision: ModelosWeather.Prevision?) {
        mPrevisionLecturaFragment?.recogerDatos(prevision)
        mPrevisionGraficoFragment?.iniciarDatos(prevision)
        if (mFrameLayoutTablet != null) {
            mPrevisionLecturaFragment?.ocultarBotonGrafico()
        }
    }

    @VisibleForTesting
    fun llamarServidorPrevision(presion: ModelosWeather.PosicionUsuario) {
        disposable = openWeatherServicio.prevision(presion.latitud, presion.longitud)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { prevision -> previsioRecibida(prevision) },
                        { error -> L.d("Error ${error.message}") })
    }

}

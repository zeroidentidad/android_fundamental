package com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.widget.FrameLayout
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.OpenWeatherServicio
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.PrevisionGraficoFragment
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.ui.prevision.PrevisionLecturaFragment
import io.reactivex.disposables.Disposable

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

    // TODO:: funcion para crear los deferentes fragmentos de la actividad
    private fun iniciarFragmentos(savedInstanceState: Bundle?) {
        // TODO:: Cuando sea necesario, se creará una nueva instancia del fragment
        if (savedInstanceState == null && mFrameLayout != null) {
            // TODO:: Guardar una instancia del fragment para cuando necesitemos pasar datos que vengan del servidor
            // TODO:: establecer el listener para responder eventos
            // TODO:: Guardar una instancia del segundo fragment
            // TODO:: Incluir el fragmente en la vista
            // TODO:: Incluir la transicion para tablet
            if (mFrameLayoutTablet != null) {

            } else {
                // TODO:: En el caso de que estemos en un movil
            }
        }
    }

    fun onIrGraficoSeleccionado() {
        // TODO:: El evento se ha lanzado, vamos a contestarlo
        // TODO:: Añadir backstacl para poder tener el fragment en la pila de ui
    }

    @VisibleForTesting
    fun getDatosActividadPrevia() {
        // TODO:: Recoger los datos de la actividad previa
        // TODO:: En el caso de que tengamos datos llamamos al servidor con la localizacion obtenida
    }

    @VisibleForTesting
    fun previsioRecibida() {
        // TODO:: Pasar los datos al fragment para que pueda construir su ui
        if (mFrameLayoutTablet != null) {
            /// TODO:: Ocultar el boton para ir al gráfico del primer fragment, en el caso de que sea una tablet.
        }
    }

    @VisibleForTesting
    fun llamarServidorPrevision(presion: ModelosWeather.PosicionUsuario) {
        // TODO:: Llamar al nuevo servicio para obtener las temperaturas de prevision
    }

}

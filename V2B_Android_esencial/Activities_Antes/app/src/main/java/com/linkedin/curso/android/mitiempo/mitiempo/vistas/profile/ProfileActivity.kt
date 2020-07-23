package com.linkedin.curso.android.mitiempo.mitiempo.vistas.profile

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ModelosProfile
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ProfilesServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ImagenesService
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import com.linkedin.curso.android.mitiempo.mitiempo.utils.Utils.Companion.capitalizar
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ProfileActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        if (v?.id == mLlamadaBT.id) {
            // TODO: Chequear los permisos para hacer llamadas de teléfono desde una app
            // TODO:: Generar un intent para realizar una llamada de teléfono
        } else if (v?.id == mEmailBT.id) {
            // TODO:: Crear la información del email
            // TODO:: Generar un intent para mandar un mail.
        }
    }

    private lateinit var mIconIV: CircleImageView
    private lateinit var mNombreTV: TextView
    private lateinit var mEmailTV: TextView
    private lateinit var mPresionAtmTV: TextView
    private lateinit var mClimaTV: TextView
    private lateinit var mBlurImage: ImageView
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mEmailBT: Button
    private lateinit var mLlamadaBT: Button

    private lateinit var mProfile: ModelosProfile.Resultado

    private var disposable: Disposable? = null
    private val profileServicio by lazy {
        ProfilesServicio.crear()
    }

    override fun initViews() {
        mLlamadaBT = findViewById(R.id.activity_profile_llamar_bt)
        mLlamadaBT.setOnClickListener(this)
        mEmailBT = findViewById(R.id.activity_profile_email_bt)
        mEmailBT.setOnClickListener(this)
        mPresionAtmTV = findViewById(R.id.activity_profile_presion_atm_tv)
        mClimaTV = findViewById(R.id.activity_profile_clima_tv)
        mNombreTV = findViewById(R.id.activity_profile_name_tv)
        mEmailTV = findViewById(R.id.activity_profile_email_tv)
        mIconIV = findViewById(R.id.activity_profile_icon_civ)
        mBlurImage = findViewById(R.id.activity_profile_icon_container)
        mProgressBar = findViewById(R.id.activity_profile_progress_bar)
        mProgressBar.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
        getInfoProfile()
        getDatosActividadPrevia()
    }

    private fun getDatosActividadPrevia() {
        // TODO:: recoger los datos simples que hemos agregado en el Extra del intent
        // TODO:: recoger los datos más complejos que hemos agregado en el Extra del intent
    }

    private fun getInfoProfile() {
        // TODO:: Chequear la versión de android sobre la que esta corriendo nuestra app
        // TODO:: Recoger el país que tienen configurado el dispositivo para versiones mayores o iguales de Android N
        // TODO:: Recoger el país que tienen configurado el dispositivo para versiones menores de Android N


        // Llamamos a un servicio de ejemplo para coger datos de profiles
        disposable = profileServicio.getProfile("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { profile -> rellenarDatosProfile(profile) },
                        { error -> L.d("Error ${error.message}") })
    }

    private fun rellenarDatosProfile(profile: ModelosProfile.Resultado) {
        // Rellenar los datos de la UI
        mProfile = profile
        val imagenesService = ImagenesService()
        imagenesService.getImagen(mIconIV, url = profile.results[0].picture.large)
        imagenesService.getBlurImage(baseContext, mBlurImage, url = profile.results[0].picture.large)
        val nombre = capitalizar(profile.results[0].name.first) + " " + capitalizar(profile.results[0].name.last)
        mNombreTV.text = nombre
        mEmailTV.text = profile.results[0].email
        mProgressBar.visibility = View.GONE
    }

    // TODO:: Generar NavigationUP
    override fun onSupportNavigateUp(): Boolean {
        // TODO:: Devolver la navegación mediante navigatieUp
        return true
    }
}

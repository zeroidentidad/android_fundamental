package com.linkedin.curso.android.mitiempo.mitiempo.vistas.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ModelosProfile
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.profile.ProfilesServicio
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ExtraNames
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
            if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
                }
            }
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + mProfile.results[0].phone)
            startActivity(intent)
        } else if (v?.id == mEmailBT.id) {
            var mailto = "mailto:" + mProfile.results[0].email +
                    "?cc=" + "alice@example.com" +
                    "&subject=" + Uri.encode("Mi titulo") +
                    "&body=" + Uri.encode("El cuerpo de mi email")
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse(mailto)
            startActivity(emailIntent)
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
        var presion = (intent?.extras?.getInt(ExtraNames.PRESION_ATMOS_INFO))?.div(100)
        mPresionAtmTV.text = "Presion atmosférica $presion%"
        var temperatura: ModelosWeather.InformacionImprescindible? = intent?.extras?.getParcelable(ExtraNames.TEMPERATURA_INFO)
        mClimaTV.text = "Temperatura ${temperatura?.temp} º, ${temperatura?.description}"
    }

    private fun getInfoProfile() {
        var pais = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            baseContext.resources.configuration.locales.get(0).country
        } else {
            baseContext.resources.configuration.locale.country
        }

        // Llamamos a un servicio de ejemplo para coger datos de profiles
        disposable = profileServicio.getProfile(pais)
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

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.activity_saludo_nav).navigateUp()
    }
}

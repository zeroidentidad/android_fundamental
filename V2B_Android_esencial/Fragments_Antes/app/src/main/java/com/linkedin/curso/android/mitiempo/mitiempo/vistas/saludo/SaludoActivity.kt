package com.linkedin.curso.android.mitiempo.mitiempo.vistas.saludo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.OpenWeatherServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ExtraNames
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ImagenesService
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.profile.ProfileActivity
import com.mindorks.paracamera.Camera
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SaludoActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mTiempoIV: ImageView
    private lateinit var mPrevisionHoyTV: TextView
    private lateinit var mTempActTV: TextView
    private lateinit var mTemMaxMin: TextView
    private lateinit var mGoToProfileBT: Button
    private lateinit var mGoToPrevisionBT: Button
    private lateinit var mLanzarCamara: FloatingActionButton
    private lateinit var mFoto: ImageView

    private lateinit var mPosicionUsuarioParcelable: ModelosWeather.PosicionUsuario

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var disposable: Disposable? = null

    private lateinit var mTiempo: ModelosWeather.ResultadoTiempoActual

    private val openWeatherServicio by lazy {
        OpenWeatherServicio.crear()
    }

    private lateinit var mCamara: Camera

    companion object {
        var FOTO_TOMADA_REQUEST = 1
    }

    override fun onClick(v: View?) {
        if (v?.id == mGoToProfileBT.id) {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(ExtraNames.PRESION_ATMOS_INFO, this.mTiempo.main.pressure)
            var miParcelable = ModelosWeather.InformacionImprescindible(mTiempo.main.temp, mTiempo.weather[0].description)
            intent.putExtra(ExtraNames.TEMPERATURA_INFO, miParcelable)
            startActivity(intent)
        }
        if (v?.id == mLanzarCamara.id) {
            try {
                mCamara.takePicture()
            } catch (e: Exception) {
                L.e("Exception en camara", e)
            }

        }
        if (v?.id == mGoToPrevisionBT.id) {
            // TODO:: Llamar a la siguiente actividad con los datos que habíamos recogido sobre la localización
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == FOTO_TOMADA_REQUEST) {
            val bitmap = mCamara.cameraBitmap
            if (bitmap != null) {
                mFoto.setImageBitmap(bitmap)
            } else {
                L.d("Imagen no tomada")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)
        initViews()
        setupLocalizacion()
        // Más información sobre la librería de cámara en https://github.com/janishar/ParaCamera
        mCamara = Camera.Builder()
                .resetToCorrectOrientation(true)
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("img" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .build(this)
    }

    private fun setupLocalizacion() {
        if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        mPosicionUsuarioParcelable = ModelosWeather.PosicionUsuario(longitud = location.longitude, latitud = location.latitude)
                        disposable = openWeatherServicio.tiempoActual(location.latitude, location.longitude)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        { temperatura -> temperaturaInfoRecibida(temperatura) },
                                        { error -> L.d("Error ${error.message}") })
                    }
                }
    }

    private fun temperaturaInfoRecibida(temperatura: ModelosWeather.ResultadoTiempoActual?) {
        mTiempo = temperatura!!
        val imagenesService = ImagenesService()
        temperatura.weather.get(0).let {
            it.icon.let { it1 -> imagenesService.getImageOpenW(mTiempoIV, it1) }
            it.description.let { it1 -> mPrevisionHoyTV.text = it1 }
        }
        temperatura.main.let {
            mTempActTV.text = it.temp.toString()
            mTemMaxMin.text = it.temp_max.toString() + "/" + it.temp_min.toString()
        }
    }

    override fun initViews() {
        mFoto = findViewById(R.id.activity_saludo_foto)
        mLanzarCamara = findViewById(R.id.activity_saludo_camara_fb)
        mLanzarCamara.setOnClickListener(this)
        mTiempoIV = findViewById(R.id.activity_saludo_tiempo_image)
        mPrevisionHoyTV = findViewById(R.id.activity_saludo_tiempo_prevision_hoy_tv)
        mTempActTV = findViewById(R.id.activity_saludo_tiempo_prevision_hoy_tem_act_tv)
        mTemMaxMin = findViewById(R.id.activity_saludo_tiempo_prevision_hoy_tem_max_min_tv)
        mGoToProfileBT = findViewById(R.id.activity_saludo_go_profile_bt)
        mGoToProfileBT.setOnClickListener(this)
        mGoToPrevisionBT = findViewById(R.id.activity_saludo_go_prevision_bt)
        mGoToPrevisionBT.setOnClickListener(this)
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCamara.deleteImage()
    }
}

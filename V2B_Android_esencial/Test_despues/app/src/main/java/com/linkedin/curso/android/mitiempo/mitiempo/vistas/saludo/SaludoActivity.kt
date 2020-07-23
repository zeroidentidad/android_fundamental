package com.linkedin.curso.android.mitiempo.mitiempo.vistas.saludo

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.graphics.Palette
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.ModelosWeather
import com.linkedin.curso.android.mitiempo.mitiempo.repositorio.servidor.weather.OpenWeatherServicio
import com.linkedin.curso.android.mitiempo.mitiempo.utils.*
import com.linkedin.curso.android.mitiempo.mitiempo.utils.ExtraNames.Companion.POSICION_USUARIO_INFO
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.prevision.PrevisionActivity
import com.linkedin.curso.android.mitiempo.mitiempo.vistas.profile.ProfileActivity
import com.mindorks.paracamera.Camera
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_saludo.*
import kotlinx.android.synthetic.main.activity_saludo_app_bar.*


class SaludoActivity : BaseActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mTiempoIV: ImageView
    private lateinit var mPrevisionHoyTV: TextView
    private lateinit var mTempActTV: TextView
    private lateinit var mTemMaxMin: TextView
    private lateinit var mGoToPrevisionBT: Button
    private lateinit var mLanzarCamara: FloatingActionButton
    private lateinit var mFoto: ImageView

    private lateinit var mPosicionUsuarioParcelable: ModelosWeather.PosicionUsuario

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var disposable: Disposable? = null

    private lateinit var mTiempo: ModelosWeather.ResultadoTiempoActual

    private lateinit var mLocation: Location

    private val openWeatherServicio by lazy {
        OpenWeatherServicio.crear()
    }

    private lateinit var mCamara: Camera

    companion object {
        var FOTO_TOMADA_REQUEST = 1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_action_profile -> {
                irAlProfile()
                true
            }
            R.id.menu_action_localizacion -> {
                Dialogo.miToast(this, "${baseContext.getString(R.string.menu_location)}: ${mLocation.longitude} - ${mLocation.latitude}")
                true
            }
            R.id.menu_action_guardar -> {
                val miSharedPreferences = MiSharedPref(context = baseContext)
                miSharedPreferences.guardarString(MiSharedPref.LONGITUD_KEY, mLocation.longitude.toString())
                miSharedPreferences.guardarString(MiSharedPref.LATITUD_KEY, mLocation.latitude.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                irAlProfile()
            }
            R.id.nav_forecast -> {
                irAPrevision()
            }
            R.id.nav_share -> {
                compartirInformacion()
            }
        }

        activity_saludo_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun compartirInformacion() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_information_text))
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun irAlProfile() {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ExtraNames.PRESION_ATMOS_INFO, this.mTiempo.main.pressure)
        val miParcelable = ModelosWeather.InformacionImprescindible(mTiempo.main.temp, mTiempo.weather[0].description)
        intent.putExtra(ExtraNames.TEMPERATURA_INFO, miParcelable)
        startActivity(intent)
    }

    private fun irAPrevision() {
        val intent = Intent(this, PrevisionActivity::class.java)
        intent.putExtra(POSICION_USUARIO_INFO, mPosicionUsuarioParcelable)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        if (v?.id == mLanzarCamara.id) {
            try {
                mCamara.takePicture()
            } catch (e: Exception) {
                L.e("Exception en camara", e)
            }

        }
        if (v?.id == mGoToPrevisionBT.id) {
            irAPrevision()
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
        peticionPermisosSMS()
        // Más información sobre la librería de cámara en https://github.com/janishar/ParaCamera
        mCamara = Camera.Builder()
                .resetToCorrectOrientation(true)
                .setTakePhotoRequestCode(1)
                .setDirectory("pics")
                .setName("img" + System.currentTimeMillis())
                .setImageFormat(Camera.IMAGE_JPEG)
                .setCompression(75)
                .build(this)
        // mensajesAlUsuarioToast()
        // mensajesAlUsuarioSnackBar()
        // mensajesAlUsuarioDialog()
        // mensajeDialogoFecha()
        // mensajeDialogoHora()
        iniciarNavigationBar()
    }

    private fun peticionPermisosSMS() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS),
                10)
    }

    private fun iniciarNavigationBar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this,
                activity_saludo_drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        activity_saludo_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        activity_saludo_nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (activity_saludo_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            activity_saludo_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun mensajeDialogoHora() {
        Dialogo.crearDialogoHora(this)
    }

    private fun mensajeDialogoFecha() {
        Dialogo.crearDialogoFecha(this)
    }

    private fun mensajesAlUsuarioDialog() {
        val positivo = DialogInterface.OnClickListener { dialogo, _ ->
            Dialogo.miToast(this, "hemos pulsado ok")
            dialogo.dismiss()
        }
        val negativo = DialogInterface.OnClickListener { dialogo, _ ->
            Dialogo.miToast(this, "hemos pulsado cancelar")
            dialogo.dismiss()
        }
        val neutro = DialogInterface.OnClickListener { dialogo, _ ->
            Dialogo.miToast(this, "hemos pulsado Neutro")
            dialogo.dismiss()
        }
        Dialogo.crearDialogoSimple(this, "Políticas de privacidad", "Para continuar con nuestra aplicación es necesario aceptar nuestras políticas de privacidad",
                positivo, negativo, null)

    }

    private fun mensajesAlUsuarioToast() {
        Toast.makeText(this, "Hola Majete", Toast.LENGTH_LONG).show()
        Dialogo.miToast(this, "Esto es más rápido")

    }

    private fun mensajesAlUsuarioSnackBar() {
        // Snackbar.make(findViewById(R.id.activity_saludo_nav), "Hola soy un snack bar", Snackbar.LENGTH_SHORT).show()
        // val mySnack = Snackbar.make(findViewById(R.id.activity_saludo_nav), "Hola! yo tengo una acción para hacer", Snackbar.LENGTH_INDEFINITE)
        // mySnack.setAction("Púlsame") { mySnack.dismiss() }
        // mySnack.show()
        val snackbar = Dialogo.miSnackBar(findViewById(R.id.activity_saludo_nav), "Hola! yo tengo una acción para hacer", true)
        snackbar.setAction("Acción") { snackbar.dismiss() }
        snackbar.show()

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
                        mLocation = location
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
        temperatura.weather[0].let {
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
        mGoToPrevisionBT = findViewById(R.id.activity_saludo_go_prevision_bt)
        mGoToPrevisionBT.setOnClickListener(this)
    }

    private fun colorearBoton(boton: Button?) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_tiempo)
        Palette.from(bitmap).generate {
            it.lightVibrantSwatch?.rgb?.let { it1 -> boton?.setBackgroundColor(it1) }
        }
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

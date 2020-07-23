package com.linkedin.curso.android.mitiempo.mitiempo.vistas.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.linkedin.curso.android.mitiempo.mitiempo.BaseActivity
import com.linkedin.curso.android.mitiempo.mitiempo.R
import com.linkedin.curso.android.mitiempo.mitiempo.modelos.UserViewModel
import com.linkedin.curso.android.mitiempo.mitiempo.utils.L


class LoginActivity : BaseActivity(), View.OnClickListener {
    private val mCodigoSingIn = 9001
    private var mAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var mProgressBar: ProgressBar? = null
    private var mStatusTextView: TextView? = null
    private var mDetailTextView: TextView? = null
    private var mButtonSingIn: SignInButton? = null
    private var mButtonSingOut: Button? = null
    private var mButtonDisconnect: Button? = null
    private var mDisconnectContainer: LinearLayout? = null

    private var mContadorTextView: TextView? = null
    private var mCambioInformacionTextView: TextView? = null
    private var mCambioInformacionButton: Button? = null

    private lateinit var mUserViewModel: UserViewModel

    private var contador = 0


    override fun onClick(v: View?) {
        when (v?.id) {
            mButtonSingIn?.id -> {
                signIn()
            }
            mButtonSingOut?.id -> {
                signOut()
            }
            mButtonDisconnect?.id -> {
                revokeAccess()
            }
            else -> {
                L.v("Identificador no definido")
            }
        }
    }

    override fun initViews() {
        mProgressBar = findViewById(R.id.activity_login_progress_bar)
        mStatusTextView = findViewById(R.id.activity_login_status)
        mDetailTextView = findViewById(R.id.activity_login_detail)
        mContadorTextView = findViewById(R.id.activity_login_contador)
        mCambioInformacionTextView = findViewById(R.id.activity_login_informacion)
        mCambioInformacionButton = findViewById(R.id.activity_login_informacion_boton)
        mButtonSingIn = findViewById(R.id.activity_login_sign_in_button)
        mButtonSingOut = findViewById(R.id.activity_login_sign_out_button)
        mButtonDisconnect = findViewById(R.id.activity_login_disconnect_button)
        mDisconnectContainer = findViewById(R.id.activity_login_sign_out_and_disconnect)

        // OnClick listener
        mButtonSingIn?.setOnClickListener(this)
        mButtonSingOut?.setOnClickListener(this)
        mButtonDisconnect?.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        initViewModels()
        initObservers()
        mUserViewModel.actualizarFicheroUsuario()
    }

    override fun onResume() {
        super.onResume()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()
        updateUI(mAuth?.currentUser)
    }

    fun initViewModels() {
        // TODO:: Inicializar el  view model
        // TODO:: Crear un observer para actualizar la UI

        // TODO:: Observa el LiveDataObserve the LiveData, pasando esta actividad como el LifecycleOwner y el observer
    }

    fun initObservers() {
        // TODO:: Iniciar Observer
        // TODO:: Iniciar View Model
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // Resultado que devuelve Google en GoogleSignInApi.getSignInIntent(...);
        if (requestCode == mCodigoSingIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // La autentificación con Google ha sido satisfactoria, ahora entra Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // La autentificación con Google falla y habrá que actualizar la UI apropiadamente
                L.w("La autentificación con Google ha fallado", e)
                updateUI(null)
            }

        }
    }

    private fun updateUI(user: FirebaseUser?) {
        mProgressBar?.visibility = View.GONE
        if (user != null) {
            mStatusTextView?.text = getString(R.string.google_status_fmt, user.email)
            mDetailTextView?.text = getString(R.string.firebase_status_fmt, user.uid)
            mButtonSingIn?.visibility = View.GONE
            mDisconnectContainer?.visibility = View.VISIBLE
        } else {
            mStatusTextView?.setText(R.string.signed_out)
            mDetailTextView?.text = null
            mButtonSingIn?.visibility = View.VISIBLE
            mDisconnectContainer?.visibility = View.GONE
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        L.d("firebaseAuthWithGoogle:" + acct.id!!)
        mProgressBar?.visibility = View.VISIBLE

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth?.signInWithCredential(credential)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        L.d("signInWithCredential: Todo ha ido bien")
                        val user = mAuth?.currentUser
                        updateUI(user)
                        // TODO:: Insertar el usuario

                    } else {
                        // If sign in fails, display a message to the user.
                        L.e("signInWithCredential: ha habido un problema", task.exception!!)
                        Snackbar.make(findViewById(R.id.main_layout), "La autentificación ha fallado.", Snackbar.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    mProgressBar?.visibility = View.GONE
                }
    }

    private fun signIn() {
        startActivityForResult(mGoogleSignInClient?.signInIntent, mCodigoSingIn)
    }

    private fun signOut() {
        // Sing out de Firebase
        mAuth?.signOut()

        // Sing out de Google
        mGoogleSignInClient?.signOut()?.addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun revokeAccess() {
        // Sing out de Firebase
        mAuth?.signOut()

        // Revocar el acceso de Google
        mGoogleSignInClient?.revokeAccess()?.addOnCompleteListener(this) {
            updateUI(null)
        }
    }
}
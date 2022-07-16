package br.bruno.projetointegrador.login.viewmodel

import android.app.Activity
import android.content.Intent
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.bruno.projetointegrador.MainActivity
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.login.data.User
import br.bruno.projetointegrador.login.data.UserDAO
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class AccessViewModel : ViewModel() {


    lateinit var gso : GoogleSignInOptions
    val GOOGLE_REQUEST_CODE = 1000


    private var onUserRequestToRegister = MutableLiveData<Boolean>()
    var createUserLiveData : LiveData<Boolean> = onUserRequestToRegister

    private var onUserRequestToSignIn = MutableLiveData<Boolean>()
    var userAuthLiveData : LiveData<Boolean> = onUserRequestToSignIn

    fun onCreateUser (name : String, lastname :  String, email : String , password : String?) {
        var firebaseAuth = Firebase.auth
        if(password != null) {
            val registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password)

            registerTask.addOnCompleteListener {
                if (registerTask.isSuccessful) {
                    val user = User(firebaseAuth.currentUser?.uid!!, name, lastname, email)
                    UserDAO().insertUser(user)
                    onUserRequestToRegister.value = registerTask.isSuccessful
                }
            }
        } else {
            var firebaseAuth = Firebase.auth
            val user = User(firebaseAuth.currentUser?.uid!!, name, lastname, email)
            UserDAO().insertUser(user)
            onUserRequestToSignIn.value = true
        }
    }

    fun onEmailSignIn(email : String, password : String) : Boolean {
        var firebaseAuth = Firebase.auth
        val authTask = firebaseAuth.signInWithEmailAndPassword(email,password)

        authTask.addOnCompleteListener {
            onUserRequestToSignIn.value = authTask.isSuccessful

        }
        return onUserRequestToSignIn.value == true
    }

    fun signInGoogleConfig (activity: Activity) : Intent {


        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.google_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity,gso).signInIntent
    }

}
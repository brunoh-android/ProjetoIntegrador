package br.bruno.projetointegrador.login.viewmodel

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.bruno.projetointegrador.login.data.User
import br.bruno.projetointegrador.login.data.UserDAO
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccessViewModel : ViewModel() {

    var firebaseAuth = Firebase.auth
    lateinit var gso : GoogleSignInOptions
    val GOOGLE_REQUEST_CODE = 1000


    private var onUserRequestToRegister = MutableLiveData<Boolean>()
    var createUserLiveData : LiveData<Boolean> = onUserRequestToRegister

    private var onUserRequestToSignIn = MutableLiveData<Boolean>()
    var userAuthLiveData : LiveData<Boolean> = onUserRequestToSignIn

    fun onCreateUser (name : String, lastname :  String, email : String , password : String?) {
        if(password != null) {
            var registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password)

            registerTask.addOnCompleteListener {
                if (registerTask.isSuccessful) {
                    var user = User(firebaseAuth.currentUser?.uid!!, name, lastname, email)
                    UserDAO().insertUser(user)
                    onUserRequestToRegister.value = registerTask.isSuccessful
                }
            }
        } else {
            var user = User(firebaseAuth.currentUser?.uid!!, name, lastname, email)
            UserDAO().insertUser(user)
            onUserRequestToSignIn.value = true
        }
    }

    fun onEmailSignIn(email : String, password : String) {
        var authTask = firebaseAuth.signInWithEmailAndPassword(email,password)

        authTask.addOnCompleteListener {
            onUserRequestToSignIn.value = authTask.isSuccessful
        }
    }


    fun signInGoogleConfig (activity: Activity) : Intent {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("590170438234-0vn8p008aei89ntq22nieqtck5b8lpq0.apps.googleusercontent.com")
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(activity,gso).signInIntent
    }

}
package br.bruno.projetointegrador.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.bruno.projetointegrador.MainActivity
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.login.viewmodel.AccessViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment(R.layout.tela_login) {

    lateinit var accessViewModel: AccessViewModel
    lateinit var emailTxt : EditText
    lateinit var passwordTxt : EditText
    lateinit var signInbtn : Button
    lateinit var registerBtn : Button
    lateinit var googleBtn : SignInButton
    lateinit var firebase : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessViewModel = ViewModelProvider(this)[AccessViewModel::class.java]
        firebase = Firebase.auth
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTxt =  view.findViewById(R.id.email)
        passwordTxt = view.findViewById(R.id.password)
        signInbtn = view.findViewById(R.id.btnlogin)
        registerBtn =  view.findViewById(R.id.btncadastrar)
        googleBtn = view.findViewById(R.id.google_button)
        setupListeners()
        setupObserver()
    }

    private fun setupListeners() {
        signInbtn.setOnClickListener {
           accessViewModel.onEmailSignIn(emailTxt.text.toString(),passwordTxt.text.toString())


        }

        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        googleBtn.setOnClickListener {
            val googleSignInIntent = accessViewModel.signInGoogleConfig(requireActivity())
            startActivityForResult(googleSignInIntent,accessViewModel.GOOGLE_REQUEST_CODE)
        }
    }

    private fun setupObserver() {
        accessViewModel.userAuthLiveData.observe(viewLifecycleOwner){
            if(it) {
                findNavController().navigate(R.id.signInFragment)
                Toast.makeText(requireContext(),"Usuario Logado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(),"Erro ao realizar Login, verifique os dados digitados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == accessViewModel.GOOGLE_REQUEST_CODE) {
                val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
                val credential = GoogleAuthProvider.getCredential(accountTask.result.idToken, null)

                firebase.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = accountTask.result
                        accessViewModel.onCreateUser(
                            user.displayName.toString(),
                            user.familyName.toString(),
                            user.email.toString(),
                            null
                        )
                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "Erro", Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }

}
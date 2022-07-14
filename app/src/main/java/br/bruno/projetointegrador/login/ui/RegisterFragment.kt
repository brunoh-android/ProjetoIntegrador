package br.bruno.projetointegrador.login.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.login.viewmodel.AccessViewModel

class RegisterFragment : Fragment(R.layout.createaccount) {

    lateinit var accessViewModel: AccessViewModel
    lateinit var name : EditText
    lateinit var lastname : EditText
    lateinit var emailTxt : EditText
    lateinit var passwordTxt : EditText
    lateinit var registerbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessViewModel =  ViewModelProvider(this)[AccessViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name = view.findViewById(R.id.txtName)
        lastname = view.findViewById(R.id.txtLastName)
        emailTxt = view.findViewById(R.id.txtEmail)
        passwordTxt =  view.findViewById(R.id.txtPassword)
        registerbtn =  view.findViewById(R.id.btnRegister)
        setupListener()
        setupObserver()

    }

    private fun setupListener() {
        registerbtn.setOnClickListener {
            accessViewModel.onCreateUser(name.text.toString(), lastname.text.toString(),
                emailTxt.text.toString(),passwordTxt.text.toString())
        }
    }
    private fun setupObserver() {
        accessViewModel.createUserLiveData.observe(viewLifecycleOwner){
            if(it){
                findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
                Toast.makeText(requireContext(),"Usuario Criado com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"Erro ao Criar usuário, tente novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
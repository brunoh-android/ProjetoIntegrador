package br.bruno.projetointegrador.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class DataBase {

    fun addObjectToDataBase(id :String, movie: Any, dataPath : String){
        val dataBase = Firebase.database.getReference(dataPath)
        dataBase.child(id).setValue(movie)
    }
}
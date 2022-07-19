package br.bruno.projetointegrador.profile.viewModel

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.databinding.ProfileFragmentsBinding
import br.bruno.projetointegrador.favorites.data.FavRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ProfileViewModel (app : Application) : AndroidViewModel(app) {

    private lateinit var imageUri: Uri
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val usuarioID= currentUser?.uid ?:""
    private val repository = FavRepository(app.applicationContext)
    private var _profileMovie: MutableLiveData<Int> = MutableLiveData()
    val profileMovie: LiveData<Int> = _profileMovie


    fun fetchSizeFavorites() {


        viewModelScope.launch {
            try {
                val favMovie = repository.getAllMovies().size
                _profileMovie.value = favMovie
            } catch (ex: Exception) {
                _profileMovie.value = 0

            }
        }
    }


     fun addPostEventListener(postReference: DatabaseReference,postEventListener : (name: String, fullname: String) -> Unit
     ) {

         viewModelScope.launch {
             try {
                 postReference.child("users").child(usuarioID).get().addOnSuccessListener {
                    postEventListener.invoke(it.child("name").value as String,"${it.child("name").value} ${it.child("lastname").value}")

                 }.addOnFailureListener {
                     Log.e("firebase", "Error getting data", it)
                 }
             } catch (ex: Exception) {


             }
         }
     }
          fun uploadImageAndSaveUri(bitmap: Bitmap) {

              viewModelScope.launch {
                  try {
                      val baos = ByteArrayOutputStream()
                      val storageRef = FirebaseStorage.getInstance()
                          .reference
                          .child("users/${FirebaseAuth.getInstance().currentUser?.uid}")
                      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                      val image = baos.toByteArray()
                      val upload = storageRef.putBytes(image)

                      upload.addOnCompleteListener { uploadTask ->


                          if (uploadTask.isSuccessful) {
                              storageRef.downloadUrl.addOnCompleteListener { urlTask ->
                                  urlTask.result?.let {
                                      imageUri = it
                                      val updates = UserProfileChangeRequest.Builder()
                                          .setPhotoUri(imageUri)
                                          .build()
                                      currentUser?.updateProfile(updates)

                                  }
                              }
                          } else {
                              uploadTask.exception?.let {
                                  //   activity?.toast(it.message!!)
                              }
                          }
                      }
                  } catch (ex: Exception) {


                  }
              }


         }

    }


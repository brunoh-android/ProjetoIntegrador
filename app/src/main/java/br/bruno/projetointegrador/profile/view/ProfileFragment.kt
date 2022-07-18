package br.bruno.projetointegrador.profile.view


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.bruno.projetointegrador.databinding.ProfileFragmentsBinding
import br.bruno.projetointegrador.home.view.MoviesFragmentDirections
import br.bruno.projetointegrador.login.data.User
import br.bruno.projetointegrador.login.ui.LoginActivity
import br.bruno.projetointegrador.login.viewmodel.AccessViewModel
import br.bruno.projetointegrador.profile.viewModel.ProfileViewModel
import br.bruno.projetointegrador.utils.buildGlide
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream


class ProfileFragment : Fragment() {




    private val REQUEST_IMAGE_CAPTURE = 2
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var _binding: ProfileFragmentsBinding? = null
    private val usuarioID= currentUser?.uid ?:""
    private val database: DatabaseReference = Firebase.database.reference
    private val binding get() = _binding!!
    private val DEFAULT_IMAGE_URL = "https://picsum.photos/200"
    private lateinit var imageUri: Uri
    private val viewModel: ProfileViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        viewModel.fetchSizeFavorites()
        addPostEventListener(database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

    }






    private fun addPostEventListener(postReference: DatabaseReference) {

        postReference.child("users").child(usuarioID).get().addOnSuccessListener {
            binding.nameText.text = "${it.child("name").value}"
            binding.txtNameFull.setText( "${it.child("name").value} ${it.child("lastname").value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }



    }

    private fun setupObservers() {
        viewModel.profileMovie.observe(viewLifecycleOwner) {
            binding.favoriteLabel.text = viewModel.profileMovie.value.toString()
        }
        binding.profileImg.setOnClickListener {
            takePictureIntent()
        }
        binding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            activity?.finish()
        }

        val email = currentUser?.email.toString()
        binding.txtEmail.setText(email)

        if(currentUser?.photoUrl!=null){
            Glide.with(requireContext()).load(currentUser.photoUrl).into(binding.profileImg)
        }




    }

    private fun takePictureIntent() {


            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_IMAGE_CAPTURE
                )
            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == REQUEST_IMAGE_CAPTURE) {
        }
        var imageView = binding.profileImg

        if (Build.VERSION.SDK_INT < 28) {
            val bitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                data?.data!!
            )
            uploadImageAndSaveUri(bitmap)
            imageView.setImageBitmap(bitmap)


        } else {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, data?.data!!)
            val bitmap = ImageDecoder.decodeBitmap(source)
            uploadImageAndSaveUri(bitmap)
            imageView.setImageBitmap(bitmap)


        }



        }

    private fun uploadImageAndSaveUri(bitmap: Bitmap) {
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

    }
}
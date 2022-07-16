package br.bruno.projetointegrador.profile.view


import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.bruno.projetointegrador.databinding.ProfileFragmentsBinding
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.profile.viewModel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {



    private val REQUEST_IMAGE_CAPTURE = 2
   // private var favRepository = FavRepository()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private var _binding: ProfileFragmentsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()


    override fun onResume() {
        super.onResume()
        viewModel.fetchSizeFavorites()
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








    private fun setupObservers() {
        viewModel.profileMovie.observe(viewLifecycleOwner) {
            binding.favoriteLabel.text = viewModel.profileMovie.value.toString()
        }
        binding.profileImg.setOnClickListener {
            takePictureIntent()
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
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_IMAGE_CAPTURE
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
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
            imageView.setImageBitmap(bitmap)
        } else {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, data?.data!!)
            val bitmap = ImageDecoder.decodeBitmap(source)

            imageView.setImageBitmap(bitmap)

            val imagem = MediaStore.Images.Media.getBitmap(
                requireActivity().baseContext.contentResolver,
                data?.data
            )
            imageView.setImageBitmap(imagem)

        }

    }


}
package br.bruno.projetointegrador.home.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.viewModels.MovieDetailsViewModel
import br.bruno.projetointegrador.home.viewObjets.MoviesDetailsVo
import br.bruno.projetointegrador.utils.*
import okhttp3.internal.notify


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var synopsis: TextView
    private lateinit var voteAverage: TextView
    private lateinit var realeseDate: TextView
    private lateinit var favCheckBox: CheckBox

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poster = view.findViewById(R.id.header_detailsImage)
        title = view.findViewById(R.id.tittle_Details)
        synopsis = view.findViewById(R.id.movie_synopsis)
        voteAverage = view.findViewById(R.id.voteAverage)
        realeseDate = view.findViewById(R.id.releaseDate)
        favCheckBox = view.findViewById(R.id.Add_to_Fav_btn)

        setupObserver()
        setupListeners()
        setupView()
    }

    private fun setupView() {
        viewModel.fecthMovieById(args.id)
    }

    private fun setupListeners() {
        favCheckBox.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) {
                viewModel.upDateFav(args.id, isChecked)
            }
        }

    }


    private fun setupObserver() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is Success -> bindView(movie.data)
                else -> Toast.makeText(
                    requireContext(),
                    Error<String>().msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        viewModel.favMovie.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is Success -> Toast.makeText(
                    requireContext(),
                    "Lista de favoritos atualizada",
                    Toast.LENGTH_SHORT
                ).show()
                else -> Toast.makeText(
                    requireContext(),
                    "Um erro ocorreu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { movie ->
            when(movie){
                is Success -> favCheckBox.isChecked = movie.data
                is Error -> Toast.makeText(requireContext(), "error dataBase", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun bindView(movie: MoviesDetailsVo) {
        title.text = movie.tittle
        synopsis.text = movie.movie_synopsis
        voteAverage.text = "votos: " + movie.vote_average.toString()
        realeseDate.text = "Lançamento: " + movie.release_date
        requireView().buildGlide(IMAGE_URL, movie.poster_path, poster, 400, 400)
    }

}
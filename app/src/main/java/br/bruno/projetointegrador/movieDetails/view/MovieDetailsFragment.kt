package br.bruno.projetointegrador.movieDetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.movieDetails.viewModel.MovieDetailsViewModel
import br.bruno.projetointegrador.movieDetails.vo.MoviesDetailsVo
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.IMAGE_URL
import br.bruno.projetointegrador.utils.MyGlide
import br.bruno.projetointegrador.utils.Success


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var synopsis: TextView
    private lateinit var voteAverage : TextView
    private lateinit var realeseDate : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        poster = view.findViewById(R.id.header_detailsImage)
        title = view.findViewById(R.id.tittle_Details)
        synopsis = view.findViewById(R.id.movie_synopsis)
        voteAverage = view.findViewById(R.id.voteAverage)
        realeseDate = view.findViewById(R.id.releaseDate)

        fetchMovieById(args.id)
        setupObserver()
    }

    private fun fetchMovieById(id: Int) {
        viewModel.fecthMovieById(id)
    }

    private fun setupObserver() {
        viewModel.movieDatail.observe(viewLifecycleOwner) { movie ->
            when (movie) {
                is Success -> setupView(movie.data)
                is Error -> Toast.makeText(
                    requireContext(),
                    Error<String>().msg,
                    Toast.LENGTH_SHORT
                ).show()
                else -> Toast.makeText(
                    requireContext(),
                    "Algo alem do erro ocorreu",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupView(movie: MoviesDetailsVo) {
        title.text = movie.tittle
        synopsis.text = movie.movie_synopsis
        voteAverage.text = movie.vote_average.toString()
        realeseDate.text = movie.release_date
        MyGlide().build(
            requireContext(),
            IMAGE_URL,
            movie.backdrop_path,
            poster,
            poster.width,
            poster.height
        )
    }

}
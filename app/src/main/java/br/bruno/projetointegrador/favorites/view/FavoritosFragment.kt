package br.bruno.projetointegrador.favorites.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.viewModel.FavViewModel
import br.bruno.projetointegrador.home.HomeFragmentDirections
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Success


class FavoritosFragment : Fragment(R.layout.favoritos_fragments) {

    private val viewModel : FavViewModel by viewModels()
    private val adapter : FavMovieAdapter by lazy{
        FavMovieAdapter(requireContext(),::onDeleteClicked){
            val direction = FavoritosFragmentDirections.actionFavoritosFragmentToMovieDetailsFragment(it.id)
            findNavController().navigate(direction)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView(view)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.favMovie.observe(viewLifecycleOwner){
            when(it) {
                is Loading -> showLoading()
                is Success -> { adapter.addData(it.data) }
                else -> showError()
            }
        }
    }

    private fun showError() {
        Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Toast.makeText(requireContext(), "LOADING", Toast.LENGTH_SHORT).show()
    }

    private fun setView(view: View) {
        view.findViewById<RecyclerView>(R.id.favoritos_Rv).adapter = adapter
        viewModel.fetchMovies(requireContext())

    }

    private fun onDeleteClicked(favMovie: FavMovie) {
        viewModel.deleteMovie(requireContext(),favMovie)

    }
}
package br.bruno.projetointegrador.home.view.tabItens.popular.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.view.tabItens.popular.viewModel.PopularMoviesViewModel
import br.bruno.projetointegrador.home.view.tabItens.popular.viewModel.Result
import br.bruno.projetointegrador.home.view.tabItens.popular.vo.MoviesVO


class PopularesFragment : Fragment(R.layout.fragments_populares) {

    private val viewModel: PopularMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserver(view)
        setupView()

    }

    private fun setupView() {
        viewModel.fetchMovieList()
    }

    private fun setupObserver(view: View) {
        viewModel.movieList.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success -> {
                    preperReycclerView(it.data, view)
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), Result.Error.genericMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun preperReycclerView(data: List<MoviesVO>, view: View) {
        view.findViewById<RecyclerView>(R.id.MoviesRV).adapter = MoviesAdapter(data)

    }

}
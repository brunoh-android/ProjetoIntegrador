package br.bruno.projetointegrador.home.view.tabItens.emCartaz.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.adapter.EmCartazMoviesAdapter
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.viewModel.EmCartazMoviesViewModel
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.vo.EmCartazMoviesVO
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.viewModel.Result


class EmCartazFragment : Fragment(R.layout.fragments_em_cartaz) {

    private val viewModel: EmCartazMoviesViewModel by viewModels()

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

    private fun preperReycclerView(data: List<EmCartazMoviesVO>, view: View) {
        view.findViewById<RecyclerView>(R.id.MoviesRV).adapter = EmCartazMoviesAdapter(requireContext(),data)

    }

}
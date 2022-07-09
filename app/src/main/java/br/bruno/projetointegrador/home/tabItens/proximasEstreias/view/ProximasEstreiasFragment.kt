package br.bruno.projetointegrador.home.tabItens.peoximasEstreias.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.bruno.projetointegrador.R
import br.bruno.projetointegrador.home.HomeFragmentDirections
import br.bruno.projetointegrador.home.tabItens.peoximasEstreias.view.adapter.ProximasEstreiasAdapter
import br.bruno.projetointegrador.home.tabItens.peoximasEstreias.viewModel.ProximasEstreiasMoviesViewModel
import br.bruno.projetointegrador.home.tabItens.peoximasEstreias.vo.ProximasEstreiasMoviesVO
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Success


class ProximasEstreiasFragment : Fragment(R.layout.fragments_proximas_estreias) {

    private val viewModel: ProximasEstreiasMoviesViewModel by viewModels()

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
                is Success -> {
                    preperReycclerView(it.data, view)
                }
                is Error -> {
                    Toast.makeText(requireContext(), Error<String>().msg, Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    private fun preperReycclerView(data: List<ProximasEstreiasMoviesVO>, view: View) {
        view.findViewById<RecyclerView>(R.id.MoviesRV).adapter = ProximasEstreiasAdapter(requireContext(),data){
            val direction = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(it.id)
            findNavController().navigate(direction)
        }

    }

}
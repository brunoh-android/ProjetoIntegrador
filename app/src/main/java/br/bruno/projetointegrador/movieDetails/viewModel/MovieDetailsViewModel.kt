package br.bruno.projetointegrador.movieDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.bruno.projetointegrador.home.view.tabItens.popular.viewModel.Result

class MovieDetailsViewModel : ViewModel() {

    private val _movieDetail : MutableLiveData<Unit> = MutableLiveData()
    val movieDatail : LiveData<Unit> = _movieDetail

    fun getMovieSelected(){

    }
}
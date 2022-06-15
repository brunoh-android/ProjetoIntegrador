package br.bruno.projetointegrador.home.view.tabItens.maisVotados.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.MaisVotadosMoives_Repository
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.vo.MaisVotadosMoviesVO
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.IMAGE_URL
import br.bruno.projetointegrador.util.Error
import br.bruno.projetointegrador.util.Result
import br.bruno.projetointegrador.util.Success

import kotlinx.coroutines.launch
import retrofit2.HttpException

class MaisVotadosMoviesViewModel : ViewModel() {

    private val repository: MaisVotadosMoives_Repository = MaisVotadosMoives_Repository()

    private val _movieList: MutableLiveData<Result<List<MaisVotadosMoviesVO>>> = MutableLiveData()
    val movieList: LiveData<Result<List<MaisVotadosMoviesVO>>> = _movieList

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieList()
                val vo = response.moviesList.map {
                    MaisVotadosMoviesVO(
                        original_title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        base_url_image = IMAGE_URL,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }
}


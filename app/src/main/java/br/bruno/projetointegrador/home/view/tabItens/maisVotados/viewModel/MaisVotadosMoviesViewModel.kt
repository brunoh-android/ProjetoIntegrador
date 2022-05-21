package br.bruno.projetointegrador.home.view.tabItens.maisVotados.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.MaisVotadosMoives_Repository
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.vo.MaisVotadosMoviesVO
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.IMAGE_URL

import kotlinx.coroutines.launch
import retrofit2.HttpException

class MaisVotadosMoviesViewModel : ViewModel() {

    private val repository: MaisVotadosMoives_Repository = MaisVotadosMoives_Repository()

    private val _movieList: MutableLiveData<Result> = MutableLiveData()
    val movieList: LiveData<Result> = _movieList

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieList()
                val config = repository.fetchImage().images
                val vo = response.moviesList.map {
                    MaisVotadosMoviesVO(
                        original_title = it.original_title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        base_url_image = IMAGE_URL,
                        poster_size = config.poster_sizes
                    )
                }
                _movieList.value = Result.Success(vo)
            } catch (ex: HttpException) {
                _movieList.value = Result.Error
            }
        }
    }
}


sealed class Result {

    data class Success(
        val data: List<MaisVotadosMoviesVO>
    ) : Result()

    object Error : Result() {
        val genericMsg: String = "Ops, algo deu errado!"
    }
}
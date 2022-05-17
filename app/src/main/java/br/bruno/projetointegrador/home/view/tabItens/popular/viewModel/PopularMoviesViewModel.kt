package br.bruno.projetointegrador.home.view.tabItens.popular.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.data.TMDB_Repository
import br.bruno.projetointegrador.home.view.tabItens.popular.vo.MoviesVO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PopularMoviesViewModel : ViewModel() {

    private val repository: TMDB_Repository = TMDB_Repository()

    private val _movieList: MutableLiveData<Result> = MutableLiveData()
    val movieList: LiveData<Result> = _movieList

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieList()
                val vo = response.moviesList.map {
                    MoviesVO(
                        original_title = it.original_title,
                        vote_average = it.vote_average,
                        overview = it.overview
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
        val data: List<MoviesVO>
    ) : Result()

    object Error : Result() {
        val genericMsg: String = "Ops, algo deu errado!"
    }
}
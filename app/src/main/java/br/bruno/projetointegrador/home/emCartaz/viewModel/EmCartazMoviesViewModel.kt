package br.bruno.projetointegrador.home.emCartaz.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.home.data.MovieRepository
import br.bruno.projetointegrador.home.emCartaz.data.EmCartazMoviesRepository
import br.bruno.projetointegrador.home.emCartaz.vo.EmCartazMoviesVO
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success

import kotlinx.coroutines.launch
import retrofit2.HttpException

class EmCartazMoviesViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()

    private val _movieList: MutableLiveData<Result<List<EmCartazMoviesVO>>> = MutableLiveData()
    val movieList: LiveData<Result<List<EmCartazMoviesVO>>> = _movieList

    fun fetchMovieList() {
        viewModelScope.launch {
            try {
                val response = repository.fetchNowPlayingMovieList()
                val vo = response.moviesList.map {
                    EmCartazMoviesVO(
                        original_title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
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


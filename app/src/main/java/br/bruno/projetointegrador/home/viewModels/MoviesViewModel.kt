package br.bruno.projetointegrador.home.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.home.data.MovieRepository
import br.bruno.projetointegrador.home.viewObjets.MoviesVO
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MoviesViewModel : ViewModel() {

    private val repository: MovieRepository = MovieRepository()
    private val _movieList: MutableLiveData<Result<List<MoviesVO>>> = MutableLiveData()
    val movieList: LiveData<Result<List<MoviesVO>>> = _movieList
    var totalPages= 0

    fun fetchTopRatedMovieList(page : Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchTopRatedMovieList(page)
                totalPages = response.pages
                val vo = response.moviesList.map {
                   MoviesVO(
                        title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
                totalPages = response.pages
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }fun fetchPopularMovieList(page : Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchPopularMovieList(page)
                totalPages = response.pages
                val vo = response.moviesList.map {
                   MoviesVO(
                        title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
                totalPages = response.pages
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }fun fetchUpComingMovieList() {
        viewModelScope.launch {
            try {
                val response = repository.fetchUpComingMovieList()
                totalPages = response.pages
                val vo = response.moviesList.map {
                   MoviesVO(
                        title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
                totalPages = response.pages
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }
    fun fetchNowPlayingMovieList(page : Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchNowPlayingMovieList(page)
                totalPages = response.pages
                val vo = response.moviesList.map {
                   MoviesVO(
                        title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
                totalPages = response.pages
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }

}


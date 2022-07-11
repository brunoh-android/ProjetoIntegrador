package br.bruno.projetointegrador.movieDetails.viewModel

import android.content.Context
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.movieDetails.data.MovieDetailsRepository
import br.bruno.projetointegrador.movieDetails.vo.MoviesDetailsVo
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MovieDetailsViewModel : ViewModel() {

    private val repository = MovieDetailsRepository()
    private val favRepo = FavRepository()

    private val _movieDetail: MutableLiveData<Result<MoviesDetailsVo>> = MutableLiveData()
    val movieDatail: LiveData<Result<MoviesDetailsVo>> = _movieDetail

    private val _favMovie: MutableLiveData<Result<FavMovie>> = MutableLiveData()
    val favMovie: LiveData<Result<FavMovie>> = _favMovie


    fun fecthMovieById(id: Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieById(id)
                val vo = MoviesDetailsVo(
                    image_url = response.poster_path,
                    movie_synopsis = response.overview,
                    movie_tittle = response.original_title,
                    backdrop_path = response.backdrop_path,
                    popularity = response.popularity,
                    tittle = response.title,
                    release_date = response.release_date,
                    vote_average = response.vote_average,
                )
                _movieDetail.value = Success(vo)
            } catch (ex: HttpException) {
                _movieDetail.value = Error()
            }
        }
    }

    fun addToFav(id: Int, context: Context) {
        _favMovie.value = Loading()
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieById(id)
                val favMovie = FavMovie(
                    title = response.title,
                    poster_path = response.poster_path,
                    id = response.id,
                    uid = response.id
                )
                favRepo.addFav(context, favMovie)
                _favMovie.value = Success(favMovie)
            } catch (ex: HttpException) {
                _favMovie.value = Error()
            }
        }
    }
}
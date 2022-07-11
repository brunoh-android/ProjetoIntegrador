package br.bruno.projetointegrador.movieDetails.viewModel

import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.movieDetails.data.MovieDetailsRepository
import br.bruno.projetointegrador.movieDetails.vo.MoviesDetailsVo
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.zip.CheckedInputStream

class MovieDetailsViewModel : ViewModel() {

    private val repository = MovieDetailsRepository()
    private val favRepo = FavRepository()

    private val _movieDetail: MutableLiveData<Result<MoviesDetailsVo>> = MutableLiveData()
    val movieDetail: LiveData<Result<MoviesDetailsVo>> = _movieDetail

    private val _favMovie: MutableLiveData<Result<Boolean>> = MutableLiveData()
    val favMovie: LiveData<Result<Boolean>> = _favMovie

    private val _isFavorite: MutableLiveData<Result<Boolean>> = MutableLiveData()
    val isFavorite: LiveData<Result<Boolean>> = _isFavorite

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

    fun upDateFav(id: Int, context: Context, isChecked: Boolean) {
        viewModelScope.launch {
            try {
                if (isChecked) {
                    val response = repository.fetchMovieById(id)
                    val movie = FavMovie(
                        title = response.title,
                        poster_path = response.poster_path,
                        id = response.id,
                        uid = response.id
                    )
                    favRepo.addFav(context, movie)
                } else {
                    favRepo.deleteById(id, context)
                }
                _favMovie.value = Success(isChecked)
            } catch (ex: Exception) {
                _favMovie.value = Error()
            }
        }
    }


    fun isMovieFavorite(id: Int, context: Context) {
        viewModelScope.launch {
            try {
                val movie = favRepo.getMovieById(context, id)
                if (movie != null)
                    _isFavorite.value = Success(true)
                else _isFavorite.value = Success(false)

            } catch (ex: Exception) {
                _isFavorite.value = Error()
            }
        }
    }
}
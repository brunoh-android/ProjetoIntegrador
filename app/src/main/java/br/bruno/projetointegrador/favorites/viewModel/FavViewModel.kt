package br.bruno.projetointegrador.favorites.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch

class FavViewModel : ViewModel() {

    private val repository = FavRepository()

    private val _favMovie: MutableLiveData<Result<List<FavMovie>>> = MutableLiveData()
    val favMovie: LiveData<Result<List<FavMovie>>> = _favMovie

    fun fetchMovies(context: Context) {
        _favMovie.value = Loading()

        viewModelScope.launch {
            try {
                val favMovie = repository.getMovies(context)
                _favMovie.value = Success(favMovie)
            } catch (ex: Exception) {
                _favMovie.value = Error()
            }
        }
    }

    fun addMovie(context: Context, favMovie: FavMovie) {
        _favMovie.value = Loading()

        viewModelScope.launch {
            try {
                repository.addFav(context, favMovie)
                fetchMovies(context)
            } catch (ex:Exception){
                _favMovie.value = Error()
            }
        }
    }

    fun deleteMovie(context: Context, favMovie: FavMovie){
        _favMovie.value = Loading()

        viewModelScope.launch {
            try {
                repository.delete(context,favMovie)
                fetchMovies(context)
            } catch (ex:Exception){
                _favMovie.value = Error()
            }
        }
    }
}
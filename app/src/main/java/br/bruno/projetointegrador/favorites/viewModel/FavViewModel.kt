package br.bruno.projetointegrador.favorites.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class FavViewModel: ViewModel() {

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

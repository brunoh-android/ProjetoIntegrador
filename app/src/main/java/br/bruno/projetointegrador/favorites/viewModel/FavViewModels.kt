package br.bruno.projetointegrador.favorites.viewModel

import android.app.Application
import androidx.lifecycle.*
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch

class FavViewModels(app : Application) : AndroidViewModel(app) {

    private val repository = FavRepository(app.applicationContext)
    private val _favMovie: MutableLiveData<Result<List<FavMovie>>> = MutableLiveData()
    val favMovie: LiveData<Result<List<FavMovie>>> = _favMovie



    fun fetchMovies() {
        _favMovie.value = Loading()

        viewModelScope.launch {
            try {
                val favMovie = repository.getAllMovies()
                _favMovie.value = Success(favMovie)

            } catch (ex: Exception) {
                _favMovie.value = Error()
            }
        }
    }

    fun deleteMovie(favMovie: FavMovie){
        _favMovie.value = Loading()

        viewModelScope.launch {
            try {
                repository.delete(favMovie)
                fetchMovies()
            } catch (ex:Exception){
                _favMovie.value = Error()
            }
        }
    }
}

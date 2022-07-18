package br.bruno.projetointegrador.profile.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import br.bruno.projetointegrador.favorites.data.FavMovie
import br.bruno.projetointegrador.favorites.data.FavRepository
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Loading
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch

class ProfileViewModel (app : Application) : AndroidViewModel(app){


    private val repository = FavRepository(app.applicationContext)
    private var _profileMovie: MutableLiveData<Int> = MutableLiveData()
    val profileMovie: LiveData<Int> = _profileMovie


    fun fetchSizeFavorites() {


        viewModelScope.launch {
            try {
                val favMovie = repository.getAllMovies().size
                _profileMovie.value = favMovie
            } catch (ex: Exception) {
                _profileMovie.value = 0

            }
        }
    }
}
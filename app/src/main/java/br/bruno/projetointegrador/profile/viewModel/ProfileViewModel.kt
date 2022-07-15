package br.bruno.projetointegrador.profile.viewModel

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

class ProfileViewModel : ViewModel() {


    private val repository = FavRepository()
    private var _profileMovie: MutableLiveData<Int> = MutableLiveData()
    val profileMovie: LiveData<Int> = _profileMovie


    fun fetchSizeFavorites(context: Context) {


        viewModelScope.launch {
            try {
                val favMovie = repository.getAllMovies(context).size
                _profileMovie.value = favMovie
            } catch (ex: Exception) {
                _profileMovie.value = 0

            }
        }
    }
}
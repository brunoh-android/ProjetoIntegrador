package br.bruno.projetointegrador.home.tabItens.popular.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.bruno.projetointegrador.home.tabItens.popular.data.PopularMoivesRepository
import br.bruno.projetointegrador.home.tabItens.popular.vo.PopularMoviesVO
import br.bruno.projetointegrador.utils.Error
import br.bruno.projetointegrador.utils.Result
import br.bruno.projetointegrador.utils.Success
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PopularMoviesViewModel : ViewModel() {

    private val repository: PopularMoivesRepository = PopularMoivesRepository()
    private val _movieList: MutableLiveData<Result<List<PopularMoviesVO>>> = MutableLiveData()
    val movieList: LiveData<Result<List<PopularMoviesVO>>> = _movieList
    var totalPages= 0

    fun fetchMovieList(page : Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchMovieList(page)
                totalPages = response.totalPages
                val vo = response.moviesList.map {
                    PopularMoviesVO(
                        title = it.title,
                        vote_average = it.vote_average,
                        overview = it.overview,
                        poster_path = it.poster_path,
                        id = it.id
                    )
                }
                _movieList.value = Success(vo)
                totalPages = response.totalPages
            } catch (ex: HttpException) {
                _movieList.value = Error()
            }
        }
    }
}

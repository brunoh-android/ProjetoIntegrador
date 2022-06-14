package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class PopularMoivesRepository {

    private val api = retrofit.create<PopularTMDBApi>()

    suspend fun fetchMovieList(page: Int): PopularMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList(page)
    }

}
package br.bruno.projetointegrador.home.tabItens.emCartaz.data

import br.bruno.projetointegrador.home.tabItens.emCartaz.data.dto.EmCartazMoviesResponse
import br.bruno.projetointegrador.home.tabItens.popular.data.PopularTMDBApi
import br.bruno.projetointegrador.utils.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class EmCartazMoviesRepository {
    private val api = retrofit.create<EmCartazTmdbApi>()

    suspend fun fetchMovieList(): EmCartazMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
}
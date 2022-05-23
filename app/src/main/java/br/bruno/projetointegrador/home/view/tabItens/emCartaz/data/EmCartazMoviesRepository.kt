package br.bruno.projetointegrador.home.view.tabItens.emCartaz.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.data.dto.EmCartazMoviesResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmCartazMoviesRepository {
    private val api = emCartazTmdbApi

    suspend fun fetchMovieList(): EmCartazMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
    suspend fun fetchImage() : ConfigResponse = withContext(Dispatchers.IO){
        api.fetchImage()
    }
}
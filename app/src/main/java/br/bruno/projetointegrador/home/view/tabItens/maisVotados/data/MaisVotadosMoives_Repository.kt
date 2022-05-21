package br.bruno.projetointegrador.home.view.tabItens.maisVotados.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.dto.MaisVotadosMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MaisVotadosMoives_Repository {

    private val api = maisVotadosTmdbApi

    suspend fun fetchMovieList(): MaisVotadosMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
    suspend fun fetchImage() : ConfigResponse = withContext(Dispatchers.IO){
        api.fetchImage()
    }
}
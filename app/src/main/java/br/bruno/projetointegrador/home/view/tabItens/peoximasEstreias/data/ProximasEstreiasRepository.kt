package br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProximasEstreiasRepository {

    private val api = proximasEstreiasTmdbApi

    suspend fun fetchMovieList(): ProximasEstreiasMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
    suspend fun fetchImage() : ConfigResponse = withContext(Dispatchers.IO){
        api.fetchImage()
    }
}
package br.bruno.projetointegrador.home.tabItens.proximasEstreias.data

import br.bruno.projetointegrador.home.tabItens.proximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import br.bruno.projetointegrador.utils.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class ProximasEstreiasRepository {

    private val api = retrofit.create<ProximasEstreiasTmdbApi>()

    suspend fun fetchMovieList(): ProximasEstreiasMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
}
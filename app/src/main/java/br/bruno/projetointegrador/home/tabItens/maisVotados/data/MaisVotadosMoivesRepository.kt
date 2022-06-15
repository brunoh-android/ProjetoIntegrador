package br.bruno.projetointegrador.home.tabItens.maisVotados.data

import br.bruno.projetointegrador.home.tabItens.maisVotados.data.dto.MaisVotadosMoviesResponse
import br.bruno.projetointegrador.utils.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class MaisVotadosMoivesRepository {

    private val api = retrofit.create<MaisVotadosTMDBApi>()

    suspend fun fetchMovieList(): MaisVotadosMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
}
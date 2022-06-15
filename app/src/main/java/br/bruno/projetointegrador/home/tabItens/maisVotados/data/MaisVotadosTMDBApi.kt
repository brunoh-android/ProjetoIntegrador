package br.bruno.projetointegrador.home.tabItens.maisVotados.data

import br.bruno.projetointegrador.home.tabItens.maisVotados.data.dto.MaisVotadosMoviesResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET

interface MaisVotadosTMDBApi {

    @GET("/3/movie/top_rated?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList() : MaisVotadosMoviesResponse

}

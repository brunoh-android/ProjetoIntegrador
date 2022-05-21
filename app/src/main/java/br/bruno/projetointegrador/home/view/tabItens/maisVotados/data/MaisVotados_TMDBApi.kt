package br.bruno.projetointegrador.home.view.tabItens.maisVotados.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.dto.MaisVotadosMoviesResponse
import retrofit2.http.GET

interface MaisVotados_TMDBApi {

    @GET("/3/movie/top_rated?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList() : MaisVotadosMoviesResponse

    @GET("/3/configuration?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchImage() : ConfigResponse
}

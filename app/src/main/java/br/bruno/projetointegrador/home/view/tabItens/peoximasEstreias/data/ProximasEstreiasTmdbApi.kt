package br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import retrofit2.http.GET

interface ProximasEstreiasTmdbApi {

    @GET("/3/movie/upcoming?api_key=${API_KEY}&language=pt-BR")
    suspend fun fetchMovieList() : ProximasEstreiasMoviesResponse

    @GET("/3/configuration?api_key=${API_KEY}&language=pt-BR")
    suspend fun fetchImage() : ConfigResponse
}

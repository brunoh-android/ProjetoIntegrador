package br.bruno.projetointegrador.home.view.tabItens.emCartaz.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.emCartaz.data.dto.EmCartazMoviesResponse
import retrofit2.http.GET

interface EmCartazTmdbApi {
    @GET("/3/movie/now_playing?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList() : EmCartazMoviesResponse

    @GET("/3/configuration?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchImage() : ConfigResponse
}

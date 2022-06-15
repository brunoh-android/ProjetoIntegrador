package br.bruno.projetointegrador.home.tabItens.emCartaz.data

import br.bruno.projetointegrador.home.tabItens.emCartaz.data.dto.EmCartazMoviesResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET

interface EmCartazTmdbApi {
    @GET("/3/movie/now_playing?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList() : EmCartazMoviesResponse

}

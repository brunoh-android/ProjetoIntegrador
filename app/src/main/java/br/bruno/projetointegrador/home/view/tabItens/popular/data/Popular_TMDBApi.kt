package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Popular_TMDBApi {

    @GET("/3/movie/popular?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList(
        @Query("page") page:Int)  : PopularMoviesResponse

    /*
    @GET("/3/configuration?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchImage() : ConfigResponse
    */
}

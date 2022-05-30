package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularTMDBApi {

    @GET("/3/movie/popular?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList(
        @Query("page") page:Int)  : PopularMoviesResponse




}

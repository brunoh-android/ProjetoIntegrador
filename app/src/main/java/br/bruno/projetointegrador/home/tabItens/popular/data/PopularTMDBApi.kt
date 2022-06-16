package br.bruno.projetointegrador.home.tabItens.popular.data

import br.bruno.projetointegrador.home.tabItens.popular.data.dto.PopularMoviesResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularTMDBApi {

    @GET("/3/movie/popular?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieList(
        @Query("page") page:Int)  : PopularMoviesResponse




}

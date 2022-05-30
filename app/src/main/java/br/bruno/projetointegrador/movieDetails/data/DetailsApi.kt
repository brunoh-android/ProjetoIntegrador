package br.bruno.projetointegrador.movieDetails.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.API_KEY
import br.bruno.projetointegrador.movieDetails.data.dto.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {

    @GET("/3/movie/?api_Key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieByID(
        @Query("id") id : Int) : MovieDetailsResponse
}
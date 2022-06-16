package br.bruno.projetointegrador.movieDetails.data

import br.bruno.projetointegrador.movieDetails.data.dto.MovieDetailsResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApi {

    @GET("/3/movie/{id}?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieByID(
        @Path("id") id : Int) : MovieDetailsResponse
}
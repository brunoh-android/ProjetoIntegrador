package br.bruno.projetointegrador.data

import br.bruno.projetointegrador.data.dto.TMDBResponse
import retrofit2.http.GET

interface TMDBApi {

    @GET("/3/movie/popular?api_key=$API_KEY")
    suspend fun fetchMovieList() : TMDBResponse
}

package br.bruno.projetointegrador.data

import br.bruno.projetointegrador.data.dto.TMDBResponse
import retrofit2.http.GET

interface TMDBApi {

    @GET("movie/550?api_key=$API_KEY")
    suspend fun fetchMovie() : TMDBResponse
}

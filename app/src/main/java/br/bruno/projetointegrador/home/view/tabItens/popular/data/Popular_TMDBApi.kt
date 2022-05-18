package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import retrofit2.http.GET

interface Popular_TMDBApi {

    @GET("/3/movie/popular?api_key=$API_KEY")
    suspend fun fetchMovieList() : PopularMoviesResponse
}

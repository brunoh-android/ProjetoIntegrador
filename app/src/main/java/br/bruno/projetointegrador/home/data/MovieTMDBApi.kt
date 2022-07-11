package br.bruno.projetointegrador.home.data

import br.bruno.projetointegrador.home.data.dto.MovieResponse
import br.bruno.projetointegrador.home.data.dto.MovieDetailsResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieTMDBApi {

    @GET("/3/movie/now_playing?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchNowPlayingMovieList() : MovieResponse

    @GET("/3/movie/top_rated?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchTopRatedMovieList() : MovieResponse

    @GET("/3/movie/popular?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchPopularMovieList(
        @Query("page") page:Int)  : MovieResponse

    @GET("/3/movie/upcoming?api_key=${API_KEY}&language=pt-BR")
    suspend fun fetchUpComingMovieList() : MovieResponse

    @GET("/3/movie/{id}?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchMovieByID(
        @Path("id") id : Int) : MovieDetailsResponse
}
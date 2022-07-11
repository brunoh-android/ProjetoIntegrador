package br.bruno.projetointegrador.home.data

import br.bruno.projetointegrador.home.emCartaz.data.dto.NowPlayingResponse
import br.bruno.projetointegrador.home.maisVotados.data.dto.MaisVotadosMoviesResponse
import br.bruno.projetointegrador.home.popular.data.dto.PopularMoviesResponse
import br.bruno.projetointegrador.home.proximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieTMDBApi {

    @GET("/3/movie/now_playing?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchNowPlayingMovieList() : NowPlayingResponse

    @GET("/3/movie/top_rated?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchTopRatedMovieList() : MaisVotadosMoviesResponse

    @GET("/3/movie/popular?api_key=$API_KEY&language=pt-BR")
    suspend fun fetchPopularMovieList(
        @Query("page") page:Int)  : PopularMoviesResponse

    @GET("/3/movie/upcoming?api_key=${API_KEY}&language=pt-BR")
    suspend fun fetchUpComingMovieList() : ProximasEstreiasMoviesResponse
}
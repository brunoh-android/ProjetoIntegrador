package br.bruno.projetointegrador.home.data

import br.bruno.projetointegrador.home.data.dto.MovieResponse
import br.bruno.projetointegrador.home.emCartaz.data.dto.NowPlayingResponse
import br.bruno.projetointegrador.home.maisVotados.data.dto.MaisVotadosMoviesResponse
import br.bruno.projetointegrador.home.popular.data.dto.PopularMoviesResponse
import br.bruno.projetointegrador.home.proximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import br.bruno.projetointegrador.utils.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class MovieRepository {

    private val api = retrofit.create<MovieTMDBApi>()

    suspend fun fetchNowPlayingMovieList() : MovieResponse = withContext(Dispatchers.IO) {
        api.fetchNowPlayingMovieList()
    }
    suspend fun fetchTopRatedMovieList() : MovieResponse = withContext(Dispatchers.IO) {
        api.fetchTopRatedMovieList()
    }

    suspend fun fetchPopularMovieList(page: Int): MovieResponse =  withContext(Dispatchers.IO) {
        api.fetchPopularMovieList(page)
    }

    suspend fun fetchUpComingMovieList() : MovieResponse = withContext(Dispatchers.IO) {
        api.fetchUpComingMovieList()
    }
}
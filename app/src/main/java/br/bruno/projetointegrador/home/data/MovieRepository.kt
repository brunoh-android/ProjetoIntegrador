package br.bruno.projetointegrador.home.data

import br.bruno.projetointegrador.home.data.dto.MovieResponse
import br.bruno.projetointegrador.home.data.dto.MovieDetailsResponse
import br.bruno.projetointegrador.utils.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class MovieRepository {

    private val api = retrofit.create<MovieTMDBApi>()

    suspend fun fetchNowPlayingMovieList(page: Int): MovieResponse = withContext(Dispatchers.IO) {
        api.fetchNowPlayingMovieList(page)
    }

    suspend fun fetchTopRatedMovieList(page: Int): MovieResponse = withContext(Dispatchers.IO) {
        api.fetchTopRatedMovieList(page)
    }

    suspend fun fetchPopularMovieList(page: Int): MovieResponse = withContext(Dispatchers.IO) {
        api.fetchPopularMovieList(page)
    }

    suspend fun fetchUpComingMovieList(): MovieResponse = withContext(Dispatchers.IO) {
        api.fetchUpComingMovieList()
    }

    suspend fun fetchMovieById(id: Int): MovieDetailsResponse = withContext(Dispatchers.IO) {
        api.fetchMovieByID(id)
    }
}
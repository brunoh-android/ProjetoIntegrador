package br.bruno.projetointegrador.data

import br.bruno.projetointegrador.data.dto.TMDBResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TMDB_Repository {

    private val api = tmdbApi

    suspend fun fetchMovieList(): TMDBResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
}
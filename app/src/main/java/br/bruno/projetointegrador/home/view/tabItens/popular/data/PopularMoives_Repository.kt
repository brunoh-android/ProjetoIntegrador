package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMoives_Repository {

    private val api = popularTmdbApi

    suspend fun fetchMovieList(): PopularMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList()
    }
}
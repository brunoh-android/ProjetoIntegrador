package br.bruno.projetointegrador.home.view.tabItens.popular.data

import br.bruno.projetointegrador.api.ConfigResponse
import br.bruno.projetointegrador.home.view.tabItens.popular.data.dto.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class PopularMoives_Repository {

    private val api = retrofit.create<Popular_TMDBApi>()

    suspend fun fetchMovieList(page: Int): PopularMoviesResponse = withContext(Dispatchers.IO) {
        api.fetchMovieList(page)
    }

   /*
    suspend fun fetchImage() : ConfigResponse = withContext(Dispatchers.IO){
        api.fetchImage()
    }*/
}
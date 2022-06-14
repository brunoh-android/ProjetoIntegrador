package br.bruno.projetointegrador.movieDetails.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.retrofit
import br.bruno.projetointegrador.movieDetails.data.dto.MovieDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

class MovieDetailsRepository {
    private val api = retrofit.create<DetailsApi>()

    suspend fun fetchMovieById(id : Int) : MovieDetailsResponse = withContext(Dispatchers.IO) {
        api.fetchMovieByID(id)
    }

}
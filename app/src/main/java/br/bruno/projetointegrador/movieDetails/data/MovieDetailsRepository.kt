package br.bruno.projetointegrador.movieDetails.data

import br.bruno.projetointegrador.home.view.tabItens.popular.data.retrofit
import retrofit2.create

class MovieDetailsRepository {
    private val api = retrofit.create<DetailsApi>()

    suspend fun fetchMovieById(id : Int) {
        api.fetchMovieByID(id)
    }

}
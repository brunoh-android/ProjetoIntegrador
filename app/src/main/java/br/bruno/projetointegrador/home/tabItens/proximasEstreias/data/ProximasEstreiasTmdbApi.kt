package br.bruno.projetointegrador.home.tabItens.proximasEstreias.data


import br.bruno.projetointegrador.home.tabItens.proximasEstreias.data.dto.ProximasEstreiasMoviesResponse
import br.bruno.projetointegrador.utils.API_KEY
import retrofit2.http.GET

interface ProximasEstreiasTmdbApi {

    @GET("/3/movie/upcoming?api_key=${API_KEY}&language=pt-BR")
    suspend fun fetchMovieList() : ProximasEstreiasMoviesResponse

}

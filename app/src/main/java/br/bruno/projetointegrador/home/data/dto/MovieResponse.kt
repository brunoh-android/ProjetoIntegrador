package br.bruno.projetointegrador.home.data.dto

import br.bruno.projetointegrador.home.emCartaz.data.dto.EmCartazMoviesDTO
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val moviesList : List<MovieDTO>
)

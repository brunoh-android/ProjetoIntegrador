package br.bruno.projetointegrador.data.dto

import com.google.gson.annotations.SerializedName

data class TMDBResponse (
    @SerializedName("results")
    val moviesList : List<TrendingMovies>
)

package br.bruno.projetointegrador.home.tabItens.popular.data.dto

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse (
    @SerializedName("results")
    val moviesList : List<PopularMoviesDTO>,
    @SerializedName("total_pages")
    val totalPages : Int,
)

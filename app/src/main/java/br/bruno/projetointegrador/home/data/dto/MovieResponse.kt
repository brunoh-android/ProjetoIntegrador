package br.bruno.projetointegrador.home.data.dto


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val moviesList : List<MovieDTO>,
    @SerializedName("total_pages")
    val pages : Int,
    )

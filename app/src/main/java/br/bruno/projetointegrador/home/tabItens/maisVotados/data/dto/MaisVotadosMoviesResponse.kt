package br.bruno.projetointegrador.home.tabItens.maisVotados.data.dto

import com.google.gson.annotations.SerializedName

data class MaisVotadosMoviesResponse (
    @SerializedName("results")
    val moviesList : List<MaisVotadosMoviesDTO>
)

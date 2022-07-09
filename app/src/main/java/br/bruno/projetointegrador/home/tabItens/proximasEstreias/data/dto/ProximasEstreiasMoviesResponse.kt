package br.bruno.projetointegrador.home.tabItens.proximasEstreias.data.dto


import com.google.gson.annotations.SerializedName

data class ProximasEstreiasMoviesResponse (
    @SerializedName("results")
    val moviesList : List<ProximasEstreiasMoviesDTO>
)

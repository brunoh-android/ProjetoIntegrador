package br.bruno.projetointegrador.home.tabItens.peoximasEstreias.data.dto


import com.google.gson.annotations.SerializedName

data class ProximasEstreiasMoviesResponse (
    @SerializedName("results")
    val moviesList : List<ProximasEstreiasMoviesDTO>
)

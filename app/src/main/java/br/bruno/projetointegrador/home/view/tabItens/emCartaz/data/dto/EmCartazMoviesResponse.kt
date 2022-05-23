package br.bruno.projetointegrador.home.view.tabItens.emCartaz.data.dto


import com.google.gson.annotations.SerializedName

    data class EmCartazMoviesResponse (
        @SerializedName("results")
        val moviesList : List<EmCartazMoviesDTO>
    )

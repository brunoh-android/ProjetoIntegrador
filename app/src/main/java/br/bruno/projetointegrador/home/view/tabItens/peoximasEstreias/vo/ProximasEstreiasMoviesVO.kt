package br.bruno.projetointegrador.home.view.tabItens.peoximasEstreias.vo

data class ProximasEstreiasMoviesVO (
    val original_title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String?,
    val base_url_image: String,
    val poster_size : Array<String>
)

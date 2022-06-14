package br.bruno.projetointegrador.home.view.tabItens.popular.vo

data class PopularMoviesVO(
    val title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String,
    val base_url_image: String,
    val id : Int,
)

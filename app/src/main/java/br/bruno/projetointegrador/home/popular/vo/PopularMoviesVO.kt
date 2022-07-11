package br.bruno.projetointegrador.home.popular.vo

data class PopularMoviesVO(
    val title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String,
    val id : Int,
)

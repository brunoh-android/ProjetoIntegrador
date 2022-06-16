package br.bruno.projetointegrador.home.tabItens.maisVotados.vo

data class MaisVotadosMoviesVO(
    val original_title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String,
    val id :Int,
)

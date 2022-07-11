package br.bruno.projetointegrador.home.data.dto

data class MovieDTO(
    val title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String,
    val id : Int,
)

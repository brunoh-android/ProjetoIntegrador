package br.bruno.projetointegrador.home.data.dto

data class MovieDetailsResponse(
    val original_title: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val popularity: Number,
    val vote_average: Number,
    val release_date: String,
    val id : Int,
)
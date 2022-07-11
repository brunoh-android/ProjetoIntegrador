package br.bruno.projetointegrador.home.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDTO(
    val title : String,
    val vote_average: Number,
    val overview : String,
    val poster_path : String,
    val id : Int,
)

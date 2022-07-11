package br.bruno.projetointegrador.home.tabItens.popular.data.dto

 data class PopularMoviesDTO(
  val title : String,
  val vote_average: Number,
  val overview : String,
  val poster_path : String,
  val id : Int,
  var isFavorite: Boolean = false,

 )

package br.bruno.projetointegrador.home.view.tabItens.maisVotados.data.dto

 data class MaisVotadosMoviesDTO(
  val title : String,
  val vote_average: Number,
  val overview : String,
  val poster_path : String,
  val id : Int,
 )

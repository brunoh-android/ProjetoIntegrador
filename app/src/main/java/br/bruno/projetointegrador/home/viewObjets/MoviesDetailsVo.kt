package br.bruno.projetointegrador.home.viewObjets

data class MoviesDetailsVo(
    val image_url : String,
    val movie_tittle : String,
    val movie_synopsis: String,
    val tittle: String,
    val backdrop_path: String,
    val popularity: Number,
    val vote_average: Number,
    val release_date: String,
)

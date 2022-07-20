package br.bruno.projetointegrador.favorites.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavMovie(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "id") val id : Int,
    @ColumnInfo(name = "movie_tittle") val title: String,
    @ColumnInfo(name = "poster_path") val poster_path: String
)

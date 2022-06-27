package br.bruno.projetointegrador.favorites.data

import androidx.room.*

@Dao
interface FavMovieDAO {

    @Query("SELECT * FROM favmovie")
    fun getAll(): List<FavMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favMovie: FavMovie)

    @Delete
    fun delete(favMovie: FavMovie)

}

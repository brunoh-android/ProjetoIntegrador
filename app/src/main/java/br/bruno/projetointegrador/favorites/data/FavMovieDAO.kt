package br.bruno.projetointegrador.favorites.data

import androidx.room.*

@Dao
interface FavMovieDAO {

    @Query("SELECT * FROM favmovie")
    fun getAll(): List<FavMovie>

    @Query("SELECT * FROM favmovie WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): FavMovie

    @Insert(onConflict = OnConflictStrategy.REPLACE) //Ver se suspende
    fun insert(favMovie: FavMovie)

    @Delete
    fun delete(favMovie: FavMovie)

}

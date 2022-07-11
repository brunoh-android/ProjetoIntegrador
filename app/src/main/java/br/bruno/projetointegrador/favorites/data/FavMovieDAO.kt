package br.bruno.projetointegrador.favorites.data

import androidx.room.*

@Dao
interface FavMovieDAO {

    @Query("SELECT * FROM favmovie")
    suspend fun getAll(): List<FavMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favMovie: FavMovie)

    @Query("SELECT * FROM favmovie WHERE id = :id")
    suspend fun getMovieById(id: Int) : FavMovie?

    @Query("DELETE FROM favmovie WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Delete
    suspend fun delete(favMovie: FavMovie)

}

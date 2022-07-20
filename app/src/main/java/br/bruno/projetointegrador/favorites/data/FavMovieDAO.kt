package br.bruno.projetointegrador.favorites.data

import androidx.room.*

@Dao
interface FavMovieDAO {

    @Query("SELECT * FROM favmovie")
     fun getAll(): List<FavMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(favMovie: FavMovie)

    @Query("SELECT * FROM favmovie WHERE id = :id")
     fun getMovieById(id: Int) : FavMovie?

    @Query("DELETE FROM favmovie WHERE id = :id")
     fun deleteById(id: Int)

    @Delete
     fun delete(favMovie: FavMovie)

}

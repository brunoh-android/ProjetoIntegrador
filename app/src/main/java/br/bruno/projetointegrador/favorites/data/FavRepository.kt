package br.bruno.projetointegrador.favorites.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FavRepository {

    private fun database(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "fav-movie"
    ).build()

    suspend fun getAllMovies(context: Context): List<FavMovie> =
        withContext(Dispatchers.IO) {
                (database(context).favMovieDAO().getAll())
            }

    suspend fun getMovieById(context: Context, id: Int) : FavMovie? =
        withContext(Dispatchers.IO) {
            database(context).favMovieDAO().getMovieById(id)
        }


    suspend fun deleteById(id: Int, context: Context) {
        withContext(Dispatchers.IO) {
            database(context).favMovieDAO().deleteById(id)
        }
    }

    suspend fun addFav(context: Context, favMovie: FavMovie) {
        withContext(Dispatchers.IO) {
           database(context).favMovieDAO().insert(favMovie)
        }
    }

    suspend fun delete(context: Context, favMovie: FavMovie) {
        withContext(Dispatchers.IO) {
            database(context).favMovieDAO().delete(favMovie)
            }
        }

    }



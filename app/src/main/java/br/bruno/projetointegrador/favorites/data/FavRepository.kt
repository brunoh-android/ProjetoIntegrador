package br.bruno.projetointegrador.favorites.data


import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FavRepository(val context: Context) {

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "fav-movie").build()

    suspend fun getAllMovies(): List<FavMovie> =
        withContext(Dispatchers.IO) {
            db.favMovieDAO().getAll()
            }

    suspend fun getMovieById(id: Int) : FavMovie? =
        withContext(Dispatchers.IO) {
            db.favMovieDAO().getMovieById(id)
        }


    suspend fun deleteById(id: Int) {
        withContext(Dispatchers.IO) {
            db.favMovieDAO().deleteById(id)
        }
    }

    suspend fun addFav(favMovie: FavMovie) {
        withContext(Dispatchers.IO) {
            db.favMovieDAO().insert(favMovie)
        }
    }

    suspend fun delete(favMovie: FavMovie) {
        withContext(Dispatchers.IO) {
            db.favMovieDAO().delete(favMovie)
            }
        }

    }



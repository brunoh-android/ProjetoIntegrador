package br.bruno.projetointegrador.favorites.data

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FavRepository {

    private fun database(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "fav-movie"
    ).build()

    suspend fun getMovies(context: Context): List<FavMovie> =
        withContext(Dispatchers.IO) {
            suspendCoroutine {
                it.resume(database(context).favMovieDAO().getAll())
            }
        }

    suspend fun addFav(context: Context, favMovie: FavMovie) {
        withContext<Unit>(Dispatchers.IO) {
            suspendCoroutine {
                it.resume(database(context).favMovieDAO().insert(favMovie))
            }
        }
    }

    suspend fun delete(context: Context, favMovie: FavMovie) {
        withContext<Unit>(Dispatchers.IO) {
            suspendCoroutine {
                it.resume(database(context).favMovieDAO().delete(favMovie))
            }
        }
    }

    suspend fun findById(context: Context, id: Int): FavMovie =
        withContext(Dispatchers.IO) {
            suspendCoroutine {
                it.resume(database(context).favMovieDAO().findById(id))
            }
        }
}

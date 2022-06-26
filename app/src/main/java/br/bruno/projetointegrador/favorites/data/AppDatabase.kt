package br.bruno.projetointegrador.favorites.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favMovieDAO(): FavMovieDAO
}
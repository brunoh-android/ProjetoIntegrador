package br.bruno.projetointegrador.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org"
const val API_KEY = "34ed4f4013d18f8a662d23851a6a5670"

private val interceptor = HttpLoggingInterceptor {
    Log.d("RETROFIT_CLIENT", it)
}
    .apply { level = HttpLoggingInterceptor.Level.BASIC }

private val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val tmdbApi: TMDBApi = retrofit.create(TMDBApi::class.java)
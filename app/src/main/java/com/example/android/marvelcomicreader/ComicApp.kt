package com.example.android.marvelcomicreader

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ComicApp : Application() {
    companion object{
        lateinit var retrofitInstance : Retrofit
        //lateinit var comicDatabase : ComicDatabase
    }

    override fun onCreate() {
        super.onCreate()

        //comicDatabase = ComicDatabase.getDatabaseInstance(this)

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()

        retrofitInstance = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
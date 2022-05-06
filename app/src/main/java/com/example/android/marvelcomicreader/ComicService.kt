package com.example.android.marvelcomicreader

import com.example.android.marvelcomicreader.model.ComicData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicService {
    @GET("/v1/public/comics/{comicId}")
    suspend fun getComicData(
        @Path("comicId") comicId: Int,
        @Query("ts") ts: Long,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Response<ComicData>
}
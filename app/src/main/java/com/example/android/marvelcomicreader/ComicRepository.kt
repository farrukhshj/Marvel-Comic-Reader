package com.example.android.marvelcomicreader

import com.example.android.marvelcomicreader.model.ComicData

open class ComicRepository(private val comicService: ComicService) {

    suspend fun getComic(comicID: Int, timeStamp: Long, apiKey: String, hash: String): ComicData? {
        val comicResponse = comicService.getComicData(comicID,timeStamp, apiKey, hash)
        if(comicResponse.isSuccessful){
            comicResponse.body()?.let {
                return it
            }
        }
        return null
    }

}
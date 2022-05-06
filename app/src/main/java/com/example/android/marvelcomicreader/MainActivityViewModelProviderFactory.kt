package com.example.android.marvelcomicreader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelProviderFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //val comicDao = ComicApp.comicDatabase.getComicDao()
        val comicRetrofit = ComicApp.retrofitInstance.create(ComicService::class.java)
        val comicRepository = ComicRepository(comicRetrofit)
        return MainActivityViewModel(comicRepository) as T
    }

}

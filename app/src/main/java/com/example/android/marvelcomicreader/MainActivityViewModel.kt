package com.example.android.marvelcomicreader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marvelcomicreader.model.ComicData
import com.example.android.marvelcomicreader.model.DataForUi
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivityViewModel(val comicRepository: ComicRepository) : ViewModel() {
    private val comicID = 1332
    private val privateKey = "5e34819cb0c7e845b400059f3392d7a148ae00c7"
    private val apiKey = "a7736ea2d8ad8e409ebe17bad1892ee0"
    private val timeStamp = System.currentTimeMillis()/1000


    val comicData : MutableLiveData<DataForUi> = MutableLiveData()

    fun getComicData() {
        val hash = md5("$timeStamp$privateKey$apiKey")
        viewModelScope.launch{
            val data = comicRepository.getComic(comicID,timeStamp, apiKey, hash)
            data?.let {
                comicData.postValue(convertComicDataToUiData(it))
            }
        }
    }

    private fun convertComicDataToUiData(data: ComicData): DataForUi {
        return DataForUi(data.data.results[0].title,data.data.results[0].description,data.data.results[0].thumbnail.path)
    }

    fun md5(inputString: String): String {
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest
                .getInstance("MD5")
            digest.update(inputString.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) {
                    h = "0$h"
                }
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}
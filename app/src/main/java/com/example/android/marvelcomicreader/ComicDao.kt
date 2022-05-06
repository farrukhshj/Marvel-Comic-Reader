package com.example.android.marvelcomicreader

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.marvelcomicreader.model.ComicData
import kotlinx.coroutines.flow.Flow


@Dao
interface ComicDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(comic : ComicData)
//
//    @Query("select * from comicTable")
//    fun getComicData() : Flow<ComicData>
//
//    @Query("delete from comicTable")
//    suspend fun deleteComicData()
}
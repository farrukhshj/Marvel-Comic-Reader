//package com.example.android.marvelcomicreader
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.android.marvelcomicreader.model.ComicData
//
//@Database(entities = [ComicData::class], version = 1, exportSchema = false)
//abstract class ComicDatabase : RoomDatabase() {
//
//    abstract fun getComicDao(): ComicDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: ComicDatabase? = null
//        private const val DB_NAME = "comic_database"
//
//        fun getDatabaseInstance(context: Context): ComicDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    ComicDatabase::class.java,
//                    DB_NAME
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
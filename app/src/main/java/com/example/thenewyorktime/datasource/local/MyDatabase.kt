package com.example.thenewyorktime.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.thenewyorktime.datasource.local.book.dao.BookDao
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel
import com.example.thenewyorktime.datasource.local.converter.Converters

@Database(entities = [BookLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase {
            if (INSTANCE == null) {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, MyRoomDatabase::class.java, "app_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
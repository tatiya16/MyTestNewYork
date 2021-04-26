package com.example.thenewyorktime.datasource.local.book.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserts(bookLocalModels: List<BookLocalModel>)

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<BookLocalModel>

    @Query("SELECT * FROM books WHERE title LIKE '%' || :title || '%'")
    fun getBooksByTitle(title: String): List<BookLocalModel>

    @Query("DELETE FROM books")
    fun removeAll()
}
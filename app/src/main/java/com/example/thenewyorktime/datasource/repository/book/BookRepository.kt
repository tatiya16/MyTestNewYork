package com.example.thenewyorktime.datasource.repository.book

import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel
import com.example.thenewyorktime.domain.book.model.BookEntity

interface BookRepository {
    suspend fun getBookFromAPI(): Resource<List<BookEntity>>
    suspend fun getBookFromLocal(): List<BookEntity>
    fun saveBookToLocal(bookLocalModels: List<BookLocalModel>)
    fun removeBookFromLocal()
    suspend fun getBookFromLocalByTitle(title: String): List<BookEntity>
}
package com.example.thenewyorktime.datasource.local.book

import com.example.thenewyorktime.datasource.local.book.dao.BookDao
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel

class BookLocalDataSource(private val bookDao: BookDao) {

    fun saveBook(bookLocalModels: List<BookLocalModel>) {
        bookDao.inserts(bookLocalModels)
    }

    fun getAllBooks(): List<BookLocalModel> {
        return bookDao.getAllBooks()
    }

    fun removeBooks() {
        bookDao.removeAll()
    }

    fun getBookByTitle(title: String): List<BookLocalModel> {
        return bookDao.getBooksByTitle(title)
    }
}
package com.example.thenewyorktime.datasource.repository.book

import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.datasource.api.book.BookApiDataSource
import com.example.thenewyorktime.datasource.local.book.BookLocalDataSource
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel
import com.example.thenewyorktime.datasource.repository.book.mapper.BookAPIToLocalMapper
import com.example.thenewyorktime.datasource.repository.book.mapper.BookLocalToEntityMapper
import com.example.thenewyorktime.domain.book.model.BookEntity

class BookRepositoryImpl(
    private val bookApiDataSource: BookApiDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) : BookRepository {
    override suspend fun getBookFromAPI(): Resource<List<BookEntity>> {
        bookApiDataSource.getBooks().let {
            return if (it is Resource.Success) {
                bookLocalDataSource.removeBooks()
                val bookLocalModel = BookAPIToLocalMapper.map(it.data?.results)
                bookLocalDataSource.saveBook(bookLocalModel)
                Resource.Success(data = getBookFromLocal())
            } else {
                val dataFromLocal = getBookFromLocal()
                Resource.Error(data = dataFromLocal, throwable = it.throwable)
            }
        }
    }

    override suspend fun getBookFromLocal(): List<BookEntity> {
        return BookLocalToEntityMapper.map(bookLocalDataSource.getAllBooks())
    }

    override suspend fun getBookFromLocalByTitle(title: String): List<BookEntity> {
        return BookLocalToEntityMapper.map(bookLocalDataSource.getBookByTitle(title))
    }

    override fun saveBookToLocal(bookLocalModels: List<BookLocalModel>) {
        bookLocalDataSource.saveBook(bookLocalModels)
    }

    override fun removeBookFromLocal() {
        bookLocalDataSource.removeBooks()
    }
}
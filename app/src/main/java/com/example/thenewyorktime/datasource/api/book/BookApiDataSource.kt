package com.example.thenewyorktime.datasource.api.book

import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.datasource.api.book.model.ResultBook
import com.example.thenewyorktime.datasource.api.book.service.BookService
import com.example.thenewyorktime.extension.parseToApiException

class BookApiDataSource(private val bookService: BookService) {
    suspend fun getBooks(): Resource<ResultBook> {
        return bookService.getAllBook().let {
            if (it.isSuccessful) {
                Resource.Success(it.body()!!)
            } else {
                Resource.Error(throwable = it.errorBody()?.parseToApiException())
            }
        }
    }
}
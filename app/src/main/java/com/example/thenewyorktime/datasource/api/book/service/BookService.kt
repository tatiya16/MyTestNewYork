package com.example.thenewyorktime.datasource.api.book.service

import com.example.thenewyorktime.datasource.api.book.model.ResultBook
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface BookService {
    @GET("svc/topstories/v2/books.json?api-key=GDrQ2aBDKGj6DELALg9H4JeXLysN1peW")
    suspend fun getAllBook(): Response<ResultBook>
}
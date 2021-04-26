package com.example.thenewyorktime.domain.book

import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel
import com.example.thenewyorktime.datasource.repository.book.BookRepository
import com.example.thenewyorktime.datasource.repository.book.mapper.BookLocalToEntityMapper
import com.example.thenewyorktime.domain.book.model.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FetchBooksUseCase(private val bookRepository: BookRepository) {
    suspend fun execute(): Flow<Resource<List<BookEntity>>>{
        return flow {
            emit(Resource.Loading())
            emit(bookRepository.getBookFromAPI())
        }.catch {
            emit(Resource.Error(bookRepository.getBookFromLocal(), it))
        }
    }
}
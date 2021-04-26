package com.example.thenewyorktime.domain.book

import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.datasource.repository.book.BookRepository
import com.example.thenewyorktime.domain.book.model.BookEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchBookUseCase(private val bookRepository: BookRepository) {
    suspend fun execute(title: String? = null): Flow<Resource<List<BookEntity>>>{
        return flow{
            emit(Resource.Loading())
            val books = if(title.isNullOrEmpty()){
                bookRepository.getBookFromLocal()
            }else{
                bookRepository.getBookFromLocalByTitle(title)
            }
            emit(Resource.Success(data = books))
        }.catch {
            emit(Resource.Error(throwable = it))
        }
    }
}
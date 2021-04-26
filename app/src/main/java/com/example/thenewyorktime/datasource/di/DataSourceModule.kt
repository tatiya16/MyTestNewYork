package com.example.thenewyorktime.datasource.di

import com.example.thenewyorktime.datasource.api.book.BookApiDataSource
import com.example.thenewyorktime.datasource.api.book.service.BookService
import com.example.thenewyorktime.datasource.api.buildRetrofit
import com.example.thenewyorktime.datasource.local.MyRoomDatabase
import com.example.thenewyorktime.datasource.local.book.BookLocalDataSource
import com.example.thenewyorktime.datasource.repository.book.BookRepository
import com.example.thenewyorktime.datasource.repository.book.BookRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { buildRetrofit() }
    single { provideBookService(get()) }
}

fun provideBookService(retrofit: Retrofit) : BookService = retrofit.create(BookService::class.java)

val bookDataSourceModule = module {
    factory { BookApiDataSource(get()) }
    factory { provideBookLocalDataSource(get()) }
    factory<BookRepository> { BookRepositoryImpl(get(), get()) }
}

fun provideBookLocalDataSource(myRoomDatabase: MyRoomDatabase): BookLocalDataSource {
    return BookLocalDataSource(myRoomDatabase.getBookDao())
}
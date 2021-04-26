package com.example.thenewyorktime.di

import com.example.thenewyorktime.datasource.api.book.service.BookService
import com.example.thenewyorktime.datasource.api.buildRetrofit
import com.example.thenewyorktime.datasource.local.MyRoomDatabase
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single { MyRoomDatabase.getInstance(get()) }
}
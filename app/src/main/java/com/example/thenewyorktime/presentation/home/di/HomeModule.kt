package com.example.thenewyorktime.presentation.home.di

import com.example.thenewyorktime.domain.book.FetchBooksUseCase
import com.example.thenewyorktime.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { FetchBooksUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}
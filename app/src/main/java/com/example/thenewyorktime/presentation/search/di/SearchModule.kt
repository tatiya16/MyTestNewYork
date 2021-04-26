package com.example.thenewyorktime.presentation.search.di

import com.example.thenewyorktime.domain.book.SearchBookUseCase
import com.example.thenewyorktime.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory { SearchBookUseCase(get()) }
    viewModel { SearchViewModel(get()) }
}
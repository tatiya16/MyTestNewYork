package com.example.thenewyorktime

import android.app.Application
import com.example.thenewyorktime.datasource.di.apiModule
import com.example.thenewyorktime.datasource.di.bookDataSourceModule
import com.example.thenewyorktime.di.appModule
import com.example.thenewyorktime.presentation.home.di.homeModule
import com.example.thenewyorktime.presentation.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                appModule,
                apiModule,
                bookDataSourceModule,
                homeModule,
                searchModule
            )
        }
    }
}
package com.example.thenewyorktime.datasource.api

import com.example.thenewyorktime.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(): Retrofit{
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_BOOK_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .build()
}
package com.example.thenewyorktime.core

sealed class Resource<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    class Success<T>(data: T?): Resource<T>(data = data)
    class Error<T>(data: T? = null, throwable: Throwable? = null): Resource<T>(data = data, throwable = throwable)
    class Loading<T>: Resource<T>()
}
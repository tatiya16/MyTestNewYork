package com.example.thenewyorktime.extension

import com.example.thenewyorktime.datasource.api.exception.ApiException

fun Throwable.getMessage(): String {
    return if (this is ApiException)
        this.fault.faultString
    else this.message ?: ""
}
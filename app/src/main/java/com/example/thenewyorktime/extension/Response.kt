package com.example.thenewyorktime.extension

import com.example.thenewyorktime.datasource.api.exception.ApiException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import java.lang.Exception

fun ResponseBody.parseToApiException(): Throwable {
    val typeToken = object : TypeToken<ApiException>() {}.type
    return try {
        Gson().fromJson(this.string(), typeToken)
    } catch (e: Exception) {
        e
    }
}
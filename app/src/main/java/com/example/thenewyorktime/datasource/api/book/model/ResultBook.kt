package com.example.thenewyorktime.datasource.api.book.model
import com.google.gson.annotations.SerializedName


data class ResultBook(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("num_results")
    val numResults: Int,
    @SerializedName("results")
    val results: List<Book>,
    @SerializedName("section")
    val section: String,
    @SerializedName("status")
    val status: STATUS
)

enum class STATUS{
    @SerializedName("OK") OK,
    @SerializedName("FAULT") FAULT,
}
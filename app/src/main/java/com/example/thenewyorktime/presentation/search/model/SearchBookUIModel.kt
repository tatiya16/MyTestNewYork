package com.example.thenewyorktime.presentation.search.model

data class SearchBookUIModel(
    val id: Int,
    val title: String,
    val `abstract`: String,
    val multiMedia: List<MultiMediaSearchUIModel>,
    val byline: String,
    val shortUrl: String
)

data class MultiMediaSearchUIModel(
    val caption: String?,
    val copyright: String?,
    val format: String?,
    val height: Int?,
    val subtype: String?,
    val type: String?,
    val url: String?,
    val width: Int?
)
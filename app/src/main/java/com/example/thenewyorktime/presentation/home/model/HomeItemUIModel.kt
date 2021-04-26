package com.example.thenewyorktime.presentation.home.model

data class HomeUIModel(
    val id: Int,
    val title: String,
    val `abstract`: String,
    val multiMedia: List<MultiMediaHomeUIModel>,
    val shortUrl: String
)

data class MultiMediaHomeUIModel(
    val caption: String?,
    val copyright: String?,
    val format: String?,
    val height: Int?,
    val subtype: String?,
    val type: String?,
    val url: String?,
    val width: Int?
)
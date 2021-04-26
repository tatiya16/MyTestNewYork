package com.example.thenewyorktime.domain.book.model

data class BookEntity constructor(
    val id: Int = -1,
    val `abstract`: String,
    val byline: String,
    val createdDate: String,
    val desFacet: List<String>,
    val geoFacet: List<String>,
    val itemType: String,
    val kicker: String,
    val materialTypeFacet: String,
    val multimedia: List<MultimediaEntity>,
    val orgFacet: List<String>,
    val perFacet: List<String>,
    val publishedDate: String,
    val section: String,
    val shortUrl: String,
    val subsection: String,
    val title: String,
    val updatedDate: String,
    val uri: String,
    val url: String
)

data class MultimediaEntity(
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val subtype: String,
    val type: String,
    val url: String,
    val width: Int
)
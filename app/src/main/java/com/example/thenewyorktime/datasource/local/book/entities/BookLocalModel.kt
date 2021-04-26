package com.example.thenewyorktime.datasource.local.book.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookLocalModel constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var bookAbstract: String? = "",
    var byline: String? = "",
    var createdDate: String? = "",
    var desFacet: List<String>? = arrayListOf(),
    var geoFacet: List<String>? = arrayListOf(),
    var itemType: String? = "",
    var kicker: String? ="",
    var materialTypeFacet: String?="",
    var multimediaLocalModel: List<MultimediaLocalModel>? = arrayListOf(),
    var orgFacet: List<String>? = arrayListOf(),
    var perFacet: List<String>? = arrayListOf(),
    var publishedDate: String? = "",
    var section: String? = "",
    var shortUrl: String? = "",
    var subsection: String? = "",
    var title: String? = "",
    var updatedDate: String? = "",
    var uri: String? = "",
    var url: String? = ""
)

data class MultimediaLocalModel(
    val caption: String?,
    val copyright: String?,
    val format: String?,
    val height: Int?,
    val subtype: String?,
    val type: String?,
    val url: String?,
    val width: Int?
)
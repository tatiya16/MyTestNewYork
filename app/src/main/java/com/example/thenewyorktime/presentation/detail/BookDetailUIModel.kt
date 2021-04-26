package com.example.thenewyorktime.presentation.detail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDetailUIModel(
    val id: Int,
    val title: String,
    val `abstract`: String,
    val urlImage: String,
    val shortUrl: String
) : Parcelable
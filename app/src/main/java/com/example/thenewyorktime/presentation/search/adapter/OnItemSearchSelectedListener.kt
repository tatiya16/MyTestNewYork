package com.example.thenewyorktime.presentation.search.adapter

import com.example.thenewyorktime.presentation.home.model.HomeUIModel
import com.example.thenewyorktime.presentation.search.model.SearchBookUIModel

interface OnItemSearchSelectedListener {
    fun onSelected(position: Int, searchBookUIModel: SearchBookUIModel)
}
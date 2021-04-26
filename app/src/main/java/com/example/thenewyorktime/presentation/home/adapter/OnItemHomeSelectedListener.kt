package com.example.thenewyorktime.presentation.home.adapter

import com.example.thenewyorktime.presentation.home.model.HomeUIModel

interface OnItemHomeSelectedListener {
    fun onSelected(position: Int, homeUIModel: HomeUIModel)
}
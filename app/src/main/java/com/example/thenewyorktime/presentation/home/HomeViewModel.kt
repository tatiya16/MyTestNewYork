package com.example.thenewyorktime.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.domain.book.FetchBooksUseCase
import com.example.thenewyorktime.domain.book.model.BookEntity
import com.example.thenewyorktime.presentation.detail.BookDetailUIModel
import com.example.thenewyorktime.presentation.home.mapper.BookEntityToHomeItemModel
import com.example.thenewyorktime.presentation.home.mapper.HomeItemUIModelToBookDetailUIModel
import com.example.thenewyorktime.presentation.home.model.HomeUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class HomeViewModel(private val fetchBooksUseCase: FetchBooksUseCase) : ViewModel() {
    private val _homeUIModelLiveData: MutableLiveData<Resource<List<HomeUIModel>>> =
        MutableLiveData()
    val homeUIModelLiveData: LiveData<Resource<List<HomeUIModel>>>
        get() = _homeUIModelLiveData

    //Just fetch one time
    fun fetchBooks() {
        if (_homeUIModelLiveData.value != null) return

        viewModelScope.launch {
            fetchBooksUseCase.execute()
                .flowOn(Dispatchers.IO)
                .collect { handleResultFromFetching(it) }
        }
    }

    private fun handleResultFromFetching(it: Resource<List<BookEntity>>) {
        val dataMap = BookEntityToHomeItemModel.map(it.data)
        _homeUIModelLiveData.value = when (it) {
            is Resource.Success -> {
                Resource.Success(data = dataMap)
            }
            is Resource.Error -> {
                Resource.Error(data = dataMap, throwable = it.throwable)
            }
            else -> Resource.Loading()
        }
    }

    fun mapToBookDetailUIModel(homeUIModel: HomeUIModel): BookDetailUIModel {
        return HomeItemUIModelToBookDetailUIModel.map(homeUIModel)
    }
}
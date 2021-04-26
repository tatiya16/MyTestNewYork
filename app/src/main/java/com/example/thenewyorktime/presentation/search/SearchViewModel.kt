package com.example.thenewyorktime.presentation.search

import androidx.lifecycle.*
import com.example.thenewyorktime.core.Resource
import com.example.thenewyorktime.domain.book.SearchBookUseCase
import com.example.thenewyorktime.domain.book.model.BookEntity
import com.example.thenewyorktime.presentation.detail.BookDetailUIModel
import com.example.thenewyorktime.presentation.search.mapper.SearchBookMapper
import com.example.thenewyorktime.presentation.search.mapper.SearchUIModelToDetailUIModel
import com.example.thenewyorktime.presentation.search.model.SearchBookUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val searchBookUseCase: SearchBookUseCase) : ViewModel() {
    private val _searchBookLiveData: MutableLiveData<Resource<List<SearchBookUIModel>>> =
        MutableLiveData()
    val searchBookLiveData: LiveData<Resource<List<SearchBookUIModel>>>
        get() = _searchBookLiveData

    private var titleSearch: String? = null

    fun searchingBook(title: String? = null) {
        if(title == titleSearch) return
        titleSearch = title
        viewModelScope.launch {
            searchBookUseCase.execute(titleSearch)
                .flowOn(Dispatchers.IO)
                .collect {
                    handleResourceFromSearchingBook(it)
                }
        }
    }

    private fun handleResourceFromSearchingBook(resource: Resource<List<BookEntity>>) {
        val listBookSearch = SearchBookMapper.map(resource.data)
        _searchBookLiveData.value = when (resource) {
            is Resource.Success -> {
                Resource.Success(data = listBookSearch)
            }
            is Resource.Error -> {
                Resource.Error(data = listBookSearch, throwable = resource.throwable)
            }
            else -> Resource.Loading()
        }
    }

    fun mapToDetailUIModel(searchBookUIModel: SearchBookUIModel): BookDetailUIModel{
        return SearchUIModelToDetailUIModel.map(searchBookUIModel)
    }
}
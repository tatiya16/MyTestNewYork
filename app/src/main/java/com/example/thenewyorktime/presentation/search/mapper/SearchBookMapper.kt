package com.example.thenewyorktime.presentation.search.mapper

import com.example.thenewyorktime.core.BaseMapper
import com.example.thenewyorktime.domain.book.model.BookEntity
import com.example.thenewyorktime.presentation.detail.BookDetailUIModel
import com.example.thenewyorktime.presentation.search.model.MultiMediaSearchUIModel
import com.example.thenewyorktime.presentation.search.model.SearchBookUIModel

object SearchBookMapper : BaseMapper<List<BookEntity>, List<SearchBookUIModel>> {
    override fun map(input: List<BookEntity>?): List<SearchBookUIModel> {
        return input?.map {
            SearchBookUIModel(
                id = it.id,
                abstract = it.abstract,
                title = it.title,
                multiMedia = it.multimedia.map { media ->
                    MultiMediaSearchUIModel(
                        caption = media.caption,
                        copyright = media.copyright,
                        format = media.format,
                        height = media.height,
                        subtype = media.subtype,
                        type = media.type,
                        url = media.url,
                        width = media.width
                    )
                },
                byline = it.byline,
                shortUrl = it.shortUrl
            )
        } ?: listOf()
    }
}

object SearchUIModelToDetailUIModel : BaseMapper<SearchBookUIModel, BookDetailUIModel> {
    override fun map(input: SearchBookUIModel?): BookDetailUIModel {
        return BookDetailUIModel(
            id = input?.id ?: -1,
            title = input?.title ?: "",
            abstract = input?.abstract ?: "",
            urlImage = input?.multiMedia?.find { it.format == "superJumbo" }?.url ?: "",
            shortUrl = input?.shortUrl ?: ""
        )
    }
}
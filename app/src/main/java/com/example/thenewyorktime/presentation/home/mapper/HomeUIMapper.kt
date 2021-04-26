package com.example.thenewyorktime.presentation.home.mapper

import com.example.thenewyorktime.core.BaseMapper
import com.example.thenewyorktime.domain.book.model.BookEntity
import com.example.thenewyorktime.presentation.detail.BookDetailUIModel
import com.example.thenewyorktime.presentation.home.model.HomeUIModel
import com.example.thenewyorktime.presentation.home.model.MultiMediaHomeUIModel

object BookEntityToHomeItemModel : BaseMapper<List<BookEntity>, List<HomeUIModel>> {
    override fun map(input: List<BookEntity>?): List<HomeUIModel> {
        return input?.map {
            HomeUIModel(
                id = it.id,
                title = it.title,
                abstract = it.abstract,
                multiMedia = it.multimedia.map { media ->
                    MultiMediaHomeUIModel(
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
                shortUrl = it.shortUrl
            )
        } ?: listOf()
    }
}

object HomeItemUIModelToBookDetailUIModel: BaseMapper<HomeUIModel, BookDetailUIModel>{
    override fun map(input: HomeUIModel?): BookDetailUIModel {
        return BookDetailUIModel(
            id = input?.id ?: -1,
            title = input?.title ?: "",
            abstract = input?.abstract ?: "",
            urlImage = input?.multiMedia?.find { it.format == "superJumbo" }?.url ?: "",
            shortUrl = input?.shortUrl ?: ""
        )
    }

}
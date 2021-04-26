package com.example.thenewyorktime.datasource.repository.book.mapper

import com.example.thenewyorktime.core.BaseMapper
import com.example.thenewyorktime.datasource.api.book.model.Book
import com.example.thenewyorktime.datasource.local.book.entities.BookLocalModel
import com.example.thenewyorktime.datasource.local.book.entities.MultimediaLocalModel
import com.example.thenewyorktime.domain.book.model.BookEntity
import com.example.thenewyorktime.domain.book.model.MultimediaEntity

object BookAPIToLocalMapper : BaseMapper<List<Book>, List<BookLocalModel>> {
    override fun map(input: List<Book>?): List<BookLocalModel> {
        return input?.map {
            BookLocalModel(
                bookAbstract = it.abstract,
                byline = it.byline,
                createdDate = it.createdDate,
                title = it.title,
                multimediaLocalModel = it.multimediaApiModel.map { media ->
                    MultimediaLocalModel(
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
                desFacet = it.desFacet,
                geoFacet = it.geoFacet,
                url = it.url,
                uri = it.uri,
                itemType = it.itemType,
                kicker = it.kicker,
                orgFacet = it.orgFacet,
                materialTypeFacet = it.materialTypeFacet,
                perFacet = it.perFacet,
                publishedDate = it.publishedDate,
                section = it.section,
                shortUrl = it.shortUrl,
                subsection = it.subsection,
                updatedDate = it.updatedDate
            )
        } ?: listOf()
    }
}

object BookLocalToEntityMapper : BaseMapper<List<BookLocalModel>, List<BookEntity>> {
    override fun map(input: List<BookLocalModel>?): List<BookEntity> {
        return input?.map {
            BookEntity(
                abstract = it.bookAbstract ?: "",
                byline = it.byline ?: "",
                createdDate = it.createdDate ?: "",
                title = it.title ?: "",
                multimedia = it.multimediaLocalModel?.map { media ->
                    MultimediaEntity(
                        caption = media.caption ?: "",
                        copyright = media.copyright ?: "",
                        format = media.format ?: "",
                        height = media.height ?: 0,
                        subtype = media.subtype ?: "",
                        type = media.type ?: "",
                        url = media.url ?: "",
                        width = media.width ?: 0
                    )
                } ?: listOf(),
                desFacet = it.desFacet ?: listOf(),
                geoFacet = it.geoFacet ?: listOf(),
                url = it.url ?: "",
                uri = it.uri ?: "",
                itemType = it.itemType ?: "",
                kicker = it.kicker ?: "",
                orgFacet = it.orgFacet ?: listOf(),
                materialTypeFacet = it.materialTypeFacet ?: "",
                perFacet = it.perFacet ?: listOf(),
                publishedDate = it.publishedDate ?: "",
                section = it.section ?: "",
                shortUrl = it.shortUrl ?: "",
                subsection = it.subsection ?: "",
                updatedDate = it.updatedDate ?: ""
            )
        } ?: listOf()
    }
}


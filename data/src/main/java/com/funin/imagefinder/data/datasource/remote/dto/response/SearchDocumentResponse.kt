package com.funin.imagefinder.data.datasource.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SearchDocumentResponse(
    @SerialName("meta") val meta: Meta,
    @SerialName("documents") val documents: List<DocumentResponse>
)

@Serializable
data class Meta(
    @SerialName("total_count") val totalCount: Int,
    @SerialName("pageable_count") val pageableCount: Int,
    @SerialName("is_end") val isEnd: Boolean
)

@Serializable
data class DocumentResponse(
    @SerialName("collection") val collection: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("display_sitename") val displaySiteName: String,
    @SerialName("doc_url") val docUrl: String,
    @SerialName("datetime") val dateTime: LocalDateTime
)
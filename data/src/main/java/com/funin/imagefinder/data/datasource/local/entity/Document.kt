package com.funin.imagefinder.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "documents")
data class Document(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "document_id")
    val id: Long = 0L,

    @ColumnInfo(name = "collection")
    val collection: String,

    @Embedded(prefix = "image_")
    val image: Image,

    @ColumnInfo(name = "display_site_name")
    val displaySiteName: String,

    @ColumnInfo(name = "doc_url")
    val docUrl: String,

    @ColumnInfo(name = "date_time")
    val dateTime: LocalDateTime
) {

    data class Image(
        @ColumnInfo(name = "thumbnail_url")
        val thumbnailUrl: String,

        @ColumnInfo(name = "image_url")
        val imageUrl: String,

        @ColumnInfo(name = "width")
        val width: Int,

        @ColumnInfo(name = "height")
        val height: Int,
    )
}
package com.funin.imagefinder.domain.model

import com.funin.imagefinder.domain.base.DomainModel

data class DocumentDomainModel(
    val id: Long,
    val collection: String,
    val imageUrl: String,
    val isBookmark: Boolean
) : DomainModel
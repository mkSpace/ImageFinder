package com.funin.imagefinder.domain.paging

import com.funin.imagefinder.domain.base.DomainModel

data class PagedData<T>(
    val data: T,
    val paging: Paging?
) : DomainModel
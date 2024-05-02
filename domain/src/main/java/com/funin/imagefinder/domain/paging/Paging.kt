package com.funin.imagefinder.domain.paging

import com.funin.imagefinder.domain.base.DomainModel

data class Paging(
    val loadedSize: Int,
    val totalPage: Int
) : DomainModel
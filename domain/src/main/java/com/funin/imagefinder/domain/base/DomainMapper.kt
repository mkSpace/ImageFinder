package com.funin.imagefinder.domain.base

interface DomainMapper<T : DomainModel?> {

    fun toDomainModel(): T
}
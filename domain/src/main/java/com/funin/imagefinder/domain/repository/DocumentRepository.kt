package com.funin.imagefinder.domain.repository

import androidx.paging.PagingData
import com.funin.imagefinder.domain.model.DocumentDomainModel
import kotlinx.coroutines.flow.Flow

interface DocumentRepository {
    fun search(query: String): Result<Unit>
    fun getPaging(query: String): Flow<PagingData<DocumentDomainModel>>
}
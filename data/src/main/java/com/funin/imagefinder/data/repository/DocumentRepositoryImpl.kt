package com.funin.imagefinder.data.repository

import androidx.paging.PagingData
import com.funin.imagefinder.data.datasource.local.dao.DocumentDao
import com.funin.imagefinder.data.datasource.remote.DocumentRemoteDataSource
import com.funin.imagefinder.domain.model.DocumentDomainModel
import com.funin.imagefinder.domain.repository.DocumentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DocumentRepositoryImpl @Inject constructor(
    private val remoteDataSource: DocumentRemoteDataSource,
    private val localDataSource: DocumentDao
): DocumentRepository {
    override fun getPaging(query: String): Flow<PagingData<DocumentDomainModel>> {
        TODO("Not yet implemented")
    }

    override fun search(query: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}
package com.funin.imagefinder.domain.usecase.document

import androidx.paging.PagingData
import com.funin.imagefinder.domain.model.DocumentDomainModel
import com.funin.imagefinder.domain.repository.DocumentRepository
import com.funin.imagefinder.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDocumentsUseCase @Inject constructor(
    private val documentRepository: DocumentRepository
) : FlowUseCase<String, PagingData<DocumentDomainModel>>() {
    override fun invoke(params: String): Flow<PagingData<DocumentDomainModel>> {
        return documentRepository.getPaging(query = params)
    }
}
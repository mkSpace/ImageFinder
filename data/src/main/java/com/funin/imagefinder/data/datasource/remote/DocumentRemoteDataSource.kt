package com.funin.imagefinder.data.datasource.remote

import com.funin.imagefinder.data.service.DocumentService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DocumentRemoteDataSource @Inject constructor(
    private val documentService: DocumentService
) {
}
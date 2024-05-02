package com.funin.imagefinder.data.service

import com.funin.imagefinder.data.datasource.remote.dto.response.SearchDocumentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DocumentService {

    @GET("/v2/search/image")
    suspend fun search(@Query("query") query: String): SearchDocumentResponse

}
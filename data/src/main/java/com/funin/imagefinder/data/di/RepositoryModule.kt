package com.funin.imagefinder.data.di

import com.funin.imagefinder.data.repository.DocumentRepositoryImpl
import com.funin.imagefinder.domain.repository.DocumentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDocumentRepository(documentRepositoryImpl: DocumentRepositoryImpl): DocumentRepository

}
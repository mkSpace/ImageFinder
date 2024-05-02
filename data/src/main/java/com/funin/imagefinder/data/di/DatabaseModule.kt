package com.funin.imagefinder.data.di

import android.content.Context
import com.funin.imagefinder.data.datasource.local.ImageFinderDatabase
import com.funin.imagefinder.data.datasource.local.dao.DocumentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): ImageFinderDatabase =
        ImageFinderDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideDocumentDao(database: ImageFinderDatabase): DocumentDao = database.documentDao()

}
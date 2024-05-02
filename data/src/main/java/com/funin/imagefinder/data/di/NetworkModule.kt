package com.funin.imagefinder.data.di

import com.funin.imagefinder.data.BuildConfig.IS_DEBUG
import com.funin.imagefinder.data.datasource.remote.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return converterJson.asConverterFactory(DEFAULT_MEDIA_TYPE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (IS_DEBUG) {
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    companion object {
        private val DEFAULT_MEDIA_TYPE = "application/json".toMediaType()
        private val converterJson = Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }
}
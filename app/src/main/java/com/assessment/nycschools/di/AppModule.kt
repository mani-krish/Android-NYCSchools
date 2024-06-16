package com.assessment.nycschools.di

import com.assessment.nycschools.data.repositories.SchoolRepositoryImpl
import com.assessment.nycschools.data.services.WebService
import com.assessment.nycschools.data.datasources.RemoteDataSource
import com.assessment.nycschools.domain.repositories.SchoolsRepository
import com.assessment.nycschools.utils.Constants
import com.assessment.nycschools.utils.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHTTPLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideCustomInterceptor() = LoggingInterceptor()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: LoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addNetworkInterceptor(loggingInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().addConverterFactory(gsonConverterFactory)
            .baseUrl(Constants.BASE_URL).client(okHttpClient).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): WebService =
        retrofit.create(WebService::class.java)

    @Singleton
    @Provides
    fun provideDataSource(countryApiService: WebService) = RemoteDataSource(countryApiService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): SchoolsRepository =
        SchoolRepositoryImpl(remoteDataSource)
}
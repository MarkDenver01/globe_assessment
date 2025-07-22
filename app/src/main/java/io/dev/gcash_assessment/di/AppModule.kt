package io.dev.gcash_assessment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dev.gcash_assessment.data.local.dao.ProductDao
import io.dev.gcash_assessment.data.local.datasource.LocalDataSource
import io.dev.gcash_assessment.data.remote.api.ApiService
import io.dev.gcash_assessment.data.remote.datasource.RemoteDataSource
import io.dev.gcash_assessment.data.repository.ProductRepositoryImpl
import io.dev.gcash_assessment.domain.repository.ProductRepository
import io.dev.gcash_assessment.domain.usecase.ProductUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ApiService): RemoteDataSource =
        RemoteDataSource(api)

    @Provides
    @Singleton
    fun provideProductRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ProductRepository =
        ProductRepositoryImpl(
            remoteDataSource,
            localDataSource
        )

    @Provides
    @Singleton
    fun providesProductUseCase(repository: ProductRepository): ProductUseCase =
        ProductUseCase(repository)
}
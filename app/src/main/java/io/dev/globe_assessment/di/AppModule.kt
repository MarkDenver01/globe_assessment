package io.dev.globe_assessment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dev.globe_assessment.data.local.datasource.LocalDataSource
import io.dev.globe_assessment.data.remote.datasource.RemoteDataSource
import io.dev.globe_assessment.data.repository.ProductRepositoryImpl
import io.dev.globe_assessment.domain.repository.ProductRepository
import io.dev.globe_assessment.domain.usecase.GetProductByIdUseCase
import io.dev.globe_assessment.domain.usecase.GetProductUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun providesProductUseCase(repository: ProductRepository): GetProductUseCase =
        GetProductUseCase(repository)

    @Provides
    @Singleton
    fun providesProductByIdUseCase(repository: ProductRepository): GetProductByIdUseCase =
        GetProductByIdUseCase(repository)
}
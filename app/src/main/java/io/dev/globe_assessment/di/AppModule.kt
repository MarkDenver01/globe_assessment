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

/**
 * Dagger Hilt module for providing dependencies across the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [ProductRepository].
     */
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

    /**
     * Provides a singleton instance of [GetProductUseCase].
     */
    @Provides
    @Singleton
    fun providesProductUseCase(repository: ProductRepository): GetProductUseCase =
        GetProductUseCase(repository)

    /**
     * Provides a singleton instance of [GetProductByIdUseCase].
     */
    @Provides
    @Singleton
    fun providesProductByIdUseCase(repository: ProductRepository): GetProductByIdUseCase =
        GetProductByIdUseCase(repository)
}

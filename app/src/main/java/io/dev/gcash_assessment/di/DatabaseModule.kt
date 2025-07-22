package io.dev.gcash_assessment.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.dev.gcash_assessment.data.local.AppDatabase
import io.dev.gcash_assessment.data.local.dao.ProductDao
import io.dev.gcash_assessment.data.local.datasource.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "gcash_db"
        ).build()

    @Provides
    @Singleton
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(productDao: ProductDao): LocalDataSource =
        LocalDataSource(productDao)
}
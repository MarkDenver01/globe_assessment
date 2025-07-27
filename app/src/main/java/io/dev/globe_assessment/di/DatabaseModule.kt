package io.dev.globe_assessment.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.dev.globe_assessment.data.local.AppDatabase
import io.dev.globe_assessment.data.local.dao.ProductDao
import io.dev.globe_assessment.data.local.datasource.LocalDataSource
import javax.inject.Singleton

/**
 * Dagger Hilt module to provide database-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of [AppDatabase].
     */
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "globe_db"
    ).build()

    /**
     * Provides the [ProductDao] from [AppDatabase].
     */
    @Provides
    @Singleton
    fun provideProductDao(db: AppDatabase): ProductDao = db.productDao()

    /**
     * Provides a singleton instance of [LocalDataSource].
     */
    @Provides
    @Singleton
    fun provideLocalDataSource(productDao: ProductDao): LocalDataSource =
        LocalDataSource(productDao)
}

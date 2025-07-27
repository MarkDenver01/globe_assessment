package io.dev.globe_assessment.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.dev.globe_assessment.data.remote.api.ApiService
import io.dev.globe_assessment.data.remote.datasource.RemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger-Hilt module responsible for providing network-related dependencies such as Retrofit,
 * OkHttpClient, Gson, and RemoteDataSource.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL used for network requests
    private const val BASE_URL = "https://dummyjson.com/"

    /**
     * Provides a logging interceptor for HTTP request/response logging.
     * Logs the full body of HTTP requests and responses.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides a header interceptor that adds default headers (e.g., Content-Type) to every request.
     */
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        }

    /**
     * Provides a configured OkHttpClient with interceptors and timeout settings.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    /**
     * Provides a Gson instance with lenient parsing enabled.
     * Used by Retrofit to serialize/deserialize JSON.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setLenient().create()

    /**
     * Provides a configured Retrofit instance for making API calls.
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    /**
     * Provides an instance of ApiService interface that defines API endpoints.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    /**
     * Provides a RemoteDataSource which wraps ApiService for abstraction.
     */
    @Provides
    @Singleton
    fun provideRemoteDataSource(api: ApiService): RemoteDataSource =
        RemoteDataSource(api)
}

package com.malky.bostatask.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.malky.bostatask.BuildConfig
import com.malky.bostatask.data.local.GamesDao
import com.malky.bostatask.data.local.GamesDatabase
import com.malky.bostatask.data.remote.GamesService
import com.malky.bostatask.data.remote.GamesServiceImpl
import com.malky.bostatask.data.remote.RetrofitGamesService
import com.malky.bostatask.data.repositories.GamesRepository
import com.malky.bostatask.data.repositories.GamesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                val originalUrl = request.url

                val url = originalUrl.newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build()

                val requestBuilder = request.newBuilder()
                    .url(url)

                val newRequest = requestBuilder.build()
                chain.proceed(newRequest)
            }
            .callTimeout(timeout = 20, unit = TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }


    @Provides
    @Singleton
    fun provideJsonConverterFactory(json: Json): Converter.Factory = json.asConverterFactory(
        contentType = "application/json; charset=utf-8".toMediaType()
    )


    @Provides
    @Singleton
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideRemoteGamesService(retrofit: Retrofit): RetrofitGamesService {
        return retrofit.create(RetrofitGamesService::class.java)

    }

    @Provides
    @Singleton
    fun provideGamesService(service: RetrofitGamesService): GamesService {
        return GamesServiceImpl(service = service)
    }


    @Provides
    @Singleton
    fun provideGamesRepository(service: GamesService): GamesRepository {
        return GamesRepositoryImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideGamesDatabase(
        @ApplicationContext context: Context
    ): GamesDatabase = Room.databaseBuilder(
        name = "Games_Database",
        klass = GamesDatabase::class.java,
        context = context
    ).build()

    @Provides
    @Singleton
    fun provideGamesDao(db: GamesDatabase): GamesDao = db.dao

}
package com.rdissi.myfdjtest.di

import com.rdissi.myfdjtest.common.Constants
import com.rdissi.myfdjtest.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val TIMEOUT = 10L

    @Provides
    @Singleton
    fun provideApi(): ApiService =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                OkHttpClient
                    .Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .apply {
                        addInterceptor(
                            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY),
                        )
                    }.build(),
            ).addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}

package com.wodms.lotteontest.api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class ApiClientModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }


    @Provides
    fun provideGithubUserApiClient(): PhotoApiClient {
        return PhotoApiClient(provideOkHttpClient())
    }

}

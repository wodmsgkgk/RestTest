package com.wodms.lotteontest.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserApiClient @Inject constructor(provideOkHttpClient: OkHttpClient) {
    val BASE_URL = "https://api.github.com"
    val userDataService: UserApiService

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        userDataService = retrofit.create(UserApiService::class.java)
    }
}

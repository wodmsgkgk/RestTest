package com.wodms.lotteontest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiManager {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val gson =
        GsonBuilder()
            .setLenient()
            .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val todoApi: PhotoApi by lazy {
        retrofit.create(PhotoApi::class.java)
    }
}

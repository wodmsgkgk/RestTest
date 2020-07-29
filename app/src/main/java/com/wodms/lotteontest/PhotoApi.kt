package com.wodms.lotteontest

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApi {
    @GET("photos")
    fun getPhotos(): Single<List<Photo>>


    @GET("photos/{id}")
    fun getPhoto(
        @Path("id") id: Long
    ): Single<Photo>
}
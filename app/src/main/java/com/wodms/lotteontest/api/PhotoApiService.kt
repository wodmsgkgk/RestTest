package com.wodms.lotteontest.api

import com.wodms.lotteontest.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoApiService {
    @GET("photos")
    fun getPhotos(): Single<List<Photo>>


    @GET("photos/{id}")
    fun getPhoto(
        @Path("id") id: Long
    ): Single<Photo>
}
package com.wodms.lotteontest.api

import com.wodms.lotteontest.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {
    @GET("/search/users")
    fun getUserData(
        @Query("q") searchword: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Observable<SearchResponse>
}
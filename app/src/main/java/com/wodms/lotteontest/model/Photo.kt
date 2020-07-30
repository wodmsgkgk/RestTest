package com.wodms.lotteontest.model

import com.google.gson.annotations.SerializedName

class Photo(
    @SerializedName("albumId")
    val albumId: Long,

    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String
)
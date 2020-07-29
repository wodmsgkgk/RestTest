package com.wodms.lotteontest

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PhotoRepository {
    private val api = ApiManager.todoApi

    fun getTaskList(): Single<List<Photo>> =
        api.getPhotos()
            .subscribeOn(Schedulers.io())

    fun getTask(id: Long): Single<Photo> =
        api.getPhoto(id)
            .subscribeOn(Schedulers.io())
}
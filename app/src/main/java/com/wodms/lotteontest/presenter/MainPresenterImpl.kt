package com.wodms.lotteontest.presenter

import android.annotation.SuppressLint
import com.wodms.lotteontest.api.PhotoApiClient
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    val view: MainPresenter.View,
    val client: PhotoApiClient
) : MainPresenter {

    @SuppressLint("CheckResult")
    override fun getPhotoList() {
        client.userDataService.getPhotos()
            .toObservable().subscribe({ view.onDataLoaded(it) }, { view.onDataFailed() }, {})
    }
}
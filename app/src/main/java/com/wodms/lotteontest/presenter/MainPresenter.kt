package com.wodms.lotteontest.presenter

import com.wodms.lotteontest.model.Photo

interface MainPresenter {
    fun getPhotoList()
    fun onViewDestroyed()

    interface View{
        fun onDataLoaded(response : List<Photo>)
        fun onDataFailed()
        fun searchPhoto()
    }
}

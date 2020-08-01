package com.wodms.lotteontest.presenter

import com.wodms.lotteontest.model.SearchResponse

interface MainPresenter {
    fun getUserList(q: String)
    fun onViewDestroyed()

    interface View{
        fun onDataLoaded(storeResponse: SearchResponse)
        fun onDataFailed()
        fun searchUser(searchWord: String)
    }
}

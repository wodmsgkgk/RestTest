package com.wodms.lotteontest.presenter

import com.wodms.lotteontest.api.UserApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    val view: MainPresenter.View,
    val client: UserApiClient
) : MainPresenter {
    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun getUserList(q: String) {
        client.userDataService.getUserData(q, "repositories", "desc")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({view.onDataLoaded(it)},{view.onDataFailed()},{})
            .apply { compositeDisposable.add(this) }
    }

    override fun onViewDestroyed() {
        compositeDisposable.clear()
    }
}
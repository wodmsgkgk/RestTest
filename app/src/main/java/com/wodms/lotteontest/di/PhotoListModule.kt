package com.wodms.lotteontest.api

import com.wodms.lotteontest.presenter.MainPresenter
import com.wodms.lotteontest.presenter.MainPresenterImpl
import dagger.Module
import dagger.Provides

@Module(includes = [ApiClientModule::class])
class PhotoListModule(val view: MainPresenter.View) {

    @Provides
    fun provideMainPresenter(presenter: MainPresenterImpl) : MainPresenter {
        return presenter
    }

    @Provides
    fun provideMainView(): MainPresenter.View {
        return view
    }
}

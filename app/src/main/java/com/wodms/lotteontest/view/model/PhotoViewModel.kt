package com.wodms.lotteontest.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wodms.lotteontest.model.Photo
import com.wodms.lotteontest.presenter.MainPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    val photoLiveData = MutableLiveData<List<Photo>>()

    private val job = Job()
    private val networkScope = CoroutineScope(Dispatchers.Default + job)

    fun getPhotoList(presenter: MainPresenter) {
        networkScope.launch {
            presenter.getPhotoList()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

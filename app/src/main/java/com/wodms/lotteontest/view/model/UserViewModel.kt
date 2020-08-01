package com.wodms.lotteontest.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wodms.lotteontest.model.SearchResponse
import com.wodms.lotteontest.model.User
import com.wodms.lotteontest.presenter.MainPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList

class UserViewModel : ViewModel() {
    val photoLiveData = MutableLiveData<ArrayList<User>>()

    private val job = Job()
    private val networkScope = CoroutineScope(Dispatchers.Default + job)

    fun getUserList(presenter: MainPresenter, searchWord: String) {
        networkScope.launch {
            presenter.getUserList(searchWord)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}

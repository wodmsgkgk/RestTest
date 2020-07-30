package com.wodms.lotteontest.view.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wodms.lotteontest.model.Photo

class PhotoViewModel : ViewModel() {
    val photoLiveData = MutableLiveData<List<Photo>>()
}

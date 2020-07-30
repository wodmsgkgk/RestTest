package com.wodms.lotteontest

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wodms.lotteontest.api.DaggerPhotoListComponent
import com.wodms.lotteontest.api.PhotoListModule
import com.wodms.lotteontest.databinding.ActivityMainBinding
import com.wodms.lotteontest.model.Photo
import com.wodms.lotteontest.presenter.MainPresenter
import com.wodms.lotteontest.view.adapter.PhotoAdapter
import com.wodms.lotteontest.view.model.PhotoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.View {
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoViewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var component = DaggerPhotoListComponent.builder()
            .photoListModule(PhotoListModule(this))
            .build()
        component.inject(this)

        binding.myRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PhotoAdapter(emptyList())
        }

        binding.funcButton.setOnClickListener { view ->
            GlobalScope.launch {
                searchPhoto()
            }
        }

        viewModel.photoLiveData.observe(this, Observer {
            GlobalScope.launch(Dispatchers.Main) {
                (binding.myRecyclerView.adapter as PhotoAdapter).setData(it)
            }
        })
    }

    override fun onDataLoaded(response: List<Photo>) {
        viewModel.photoLiveData.postValue(response)
    }

    override fun onDataFailed() {
        Log.d("test", "onDataFailed")
    }

    override fun searchPhoto() {
        presenter.getPhotoList()
    }
}

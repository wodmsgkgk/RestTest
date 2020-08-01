package com.wodms.lotteontest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wodms.lotteontest.api.DaggerUserListComponent
import com.wodms.lotteontest.api.UserListModule
import com.wodms.lotteontest.databinding.ActivityMainBinding
import com.wodms.lotteontest.model.SearchResponse
import com.wodms.lotteontest.model.User
import com.wodms.lotteontest.presenter.MainPresenter
import com.wodms.lotteontest.view.adapter.UserAdapter
import com.wodms.lotteontest.view.model.UserViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainPresenter.View {
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var component = DaggerUserListComponent.builder()
            .userListModule(UserListModule(this))
            .build()
        component.inject(this)

        binding.myRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserAdapter(emptyList())
        }

        binding.funcButton.setOnClickListener { view ->
            searchUser(binding.editText.text.toString())
        }

        viewModel.photoLiveData.observe(this, Observer {
            (binding.myRecyclerView.adapter as UserAdapter).setData(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun onDataLoaded(storeResponse: SearchResponse) {
        viewModel.photoLiveData.postValue(storeResponse.items)
    }

    override fun onDataFailed() {
        Toast.makeText(this@MainActivity, "Data Loding Fail", Toast.LENGTH_SHORT).show()
    }

    override fun searchUser(searchWord: String) {
        if (searchWord.isBlank()) {
            viewModel.photoLiveData.setValue(arrayListOf<User>())
        } else {
            viewModel.getUserList(presenter, searchWord)
        }
    }
}

package com.wodms.lotteontest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import com.wodms.lotteontest.databinding.ActivityMainBinding
import com.wodms.lotteontest.databinding.ItemViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoReposViewModel by viewModels<PhotoReposViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PhotoAdapter(emptyList())
        }

        binding.funcButton.setOnClickListener { view ->
            GlobalScope.launch {
                viewModel.getTaskListsObservable().subscribe {
                    viewModel.photoLiveData.postValue(it)
                }
            }
        }

        viewModel.photoLiveData.observe(this, Observer {
            GlobalScope.launch(Dispatchers.Main) {
                (binding.myRecyclerView.adapter as PhotoAdapter).setData(it)
            }
        })
    }
}

class PhotoReposViewModel : ViewModel() {
    private val repository = PhotoRepository()
    val photoLiveData = MutableLiveData<List<Photo>>()

    fun getTaskListsObservable() =
        repository.getTaskList().toObservable()

    fun getPhotoObservable(id: Long) =
        repository.getTask(id).toObservable()
}


class PhotoAdapter(private var myDataset: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    lateinit var glide: RequestManager

    class PhotoViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        glide = Glide.with(view)
        return PhotoViewHolder(ItemViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding.titleText.text = myDataset[position].title
//        glide.load(myDataset[position].thumbnailUrl).into(holder.binding.thumbnailImage)
        Picasso.get().load(myDataset[position].thumbnailUrl).into(holder.binding.thumbnailImage)
    }

    override fun getItemCount() = myDataset.size

    fun setData(newData: List<Photo>) {
        myDataset = newData
        notifyDataSetChanged()
    }
}
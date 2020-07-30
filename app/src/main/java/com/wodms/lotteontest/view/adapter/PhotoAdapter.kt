package com.wodms.lotteontest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.squareup.picasso.Picasso
import com.wodms.lotteontest.R
import com.wodms.lotteontest.databinding.ItemViewBinding
import com.wodms.lotteontest.model.Photo

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
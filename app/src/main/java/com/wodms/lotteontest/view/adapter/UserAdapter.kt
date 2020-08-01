package com.wodms.lotteontest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.wodms.lotteontest.R
import com.wodms.lotteontest.databinding.ItemViewBinding
import com.wodms.lotteontest.model.User

class UserAdapter(private var myDataset: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    lateinit var glide: RequestManager

    class UserViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        glide = Glide.with(view)
        return UserViewHolder(ItemViewBinding.bind(view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.userNameText.text = myDataset[position].login
        holder.binding.userRepoText.text = myDataset[position].repos_url
        glide.load(myDataset[position].avatar_url).into(holder.binding.thumbnailImage)
    }

    override fun getItemCount() = myDataset.size

    fun setData(newData: List<User>) {
        myDataset = newData
        notifyDataSetChanged()
    }
}
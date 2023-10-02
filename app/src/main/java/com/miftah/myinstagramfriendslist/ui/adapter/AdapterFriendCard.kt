package com.miftah.myinstagramfriendslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.myinstagramfriendslist.data.remote.response.Friend
import com.miftah.myinstagramfriendslist.databinding.CardMainBinding

class AdapterFriendCard : ListAdapter<Friend, AdapterFriendCard.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: IOnClickListener

    inner class ViewHolder(val binding: CardMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friendList: Friend) {
            binding.tvNama.text = friendList.login
            Glide.with(binding.root)
                .load(friendList.avatarUrl)
                .into(binding.imgFriend)
        }
        fun callCard(friendList: Friend) {
            onItemClickCallback.onClickCard(friendList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friendList = getItem(position)
        holder.bind(friendList)
        holder.itemView.setOnClickListener {
            holder.callCard(friendList)
        }
    }

    fun setOnClickCallback(call: IOnClickListener) {
        this.onItemClickCallback = call
    }

    interface IOnClickListener {
        fun onClickCard(friendItem: Friend)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Friend>() {
            override fun areItemsTheSame(
                oldItem: Friend,
                newItem: Friend
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Friend,
                newItem: Friend
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

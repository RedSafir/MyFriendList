package com.miftah.myinstagramfriendslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.databinding.CardMainBinding

class AdapterFriendCard : ListAdapter<FriendRespond, AdapterFriendCard.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: IOnClickListener

    inner class ViewHolder(val binding: CardMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friendList: FriendRespond) {
            binding.tvNama.text = friendList.login
            Glide.with(binding.root)
                .load(friendList.avatarUrl)
                .into(binding.imgFriend)
        }
        fun callCard(friendList: FriendRespond) {
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
        fun onClickCard(friendRespondItem: FriendRespond)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FriendRespond>() {
            override fun areItemsTheSame(
                oldItem: FriendRespond,
                newItem: FriendRespond
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FriendRespond,
                newItem: FriendRespond
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

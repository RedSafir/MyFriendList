package com.miftah.myinstagramfriendslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend
import com.miftah.myinstagramfriendslist.databinding.CardMainBinding

class AdapterFavCard : ListAdapter<FavFriend, AdapterFavCard.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: IOnClickListener

    inner class ViewHolder(val binding: CardMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friendList: FavFriend) {
            binding.tvNama.text = friendList.name
            Glide.with(binding.root)
                .load(friendList.img)
                .into(binding.imgFriend)
        }
        fun callCard(friendList: FavFriend) {
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
        fun onClickCard(favFriendItem: FavFriend)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavFriend>() {
            override fun areItemsTheSame(
                oldItem: FavFriend,
                newItem: FavFriend
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FavFriend,
                newItem: FavFriend
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

package com.miftah.myinstagramfriendslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.databinding.CardMainBinding

class AdapterFriendCard : ListAdapter<FriendResponds, AdapterFriendCard.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: IOnClickListener

    class ViewHolder(val binding: CardMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friendList: FriendResponds) {
            binding.tvNama.text = friendList.login
            Glide.with(binding.root)
                .load(friendList.avatarUrl)
                .into(binding.imgFriend)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friendList = getItem(position)
        holder.bind(friendList)
    }

    fun setOnClickCallback(call: IOnClickListener) {
        this.onItemClickCallback = call
    }

    interface IOnClickListener {
        fun onClickCard(friendRespondsItem: FriendResponds)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FriendResponds>() {
            override fun areItemsTheSame(
                oldItem: FriendResponds,
                newItem: FriendResponds
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FriendResponds,
                newItem: FriendResponds
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}


//class AdapterFriendCard(
//    private val listFriends: List<FriendResponds>
//) : RecyclerView.Adapter<AdapterFriendCard.ViewHolder>() {
//
//    private lateinit var onItemClickCallback: IOnClickListener
//
//    inner class ViewHolder(val binding: CardMainBinding) : RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = listFriends.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val friendRespondsItem = listFriends[position]
//        holder.binding.tvNama.text = friendRespondsItem.login
//
//        Glide.with(holder.itemView.context)
//            .load(friendRespondsItem.avatarUrl)
//            .into(holder.binding.imgFriend)
//
//        holder.itemView.setOnClickListener {
//            onItemClickCallback.onClickCard(friendRespondsItem)
//        }
//    }
//
//    fun setOnClickCallback(call: IOnClickListener) {
//        this.onItemClickCallback = call
//    }
//
//    interface IOnClickListener {
//        fun onClickCard(friendRespondsItem: FriendResponds)
//    }
//}

package com.miftah.myinstagramfriendslist.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miftah.myinstagramfriendslist.ui.follower.FolowerFragment
import com.miftah.myinstagramfriendslist.ui.following.FollowingFragment

class AdapterTabLayout(activity : AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FolowerFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}
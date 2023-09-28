package com.miftah.myinstagramfriendslist.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.ui.follow.FollowFragment

class AdapterSelectionLayout(activity : AppCompatActivity, val data : UserRespond) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment =  FollowFragment()
        val bundle = Bundle()
        when (position) {
            0 -> bundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWER)
            1 -> bundle.putString(FollowFragment.ARG_TAB, FollowFragment.TAB_FOLLOWING)
        }
        bundle.putParcelable(FollowFragment.TAB_VALUE, data)
        fragment.arguments = bundle
        return fragment
    }
}
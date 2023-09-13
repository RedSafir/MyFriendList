package com.miftah.myinstagramfriendslist.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.data.retrofit.UserResponse
import com.miftah.myinstagramfriendslist.databinding.ActivityMainProfileBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterTabLayout
import com.miftah.myinstagramfriendslist.vmodel.ViewModelMain

class MainProfileActivity : AppCompatActivity() {

    private lateinit var mainProfileActivity: ActivityMainProfileBinding
    private val mainViewModel by viewModels<ViewModelMain>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainProfileActivity = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(mainProfileActivity.root)

        val selectionAdapter = AdapterTabLayout(this)
        val viewPager = mainProfileActivity.viewPager

        viewPager.adapter = selectionAdapter
        val tabs = mainProfileActivity.tabMenu

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TABS_TITLE[position])
        }.attach()

        val friendResponds = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<FriendResponds>(MAIN_PERSON, FriendResponds::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<FriendResponds>(MAIN_PERSON)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        setupData(friendResponds as FriendResponds)

        mainViewModel.UserResponse.observe(this) {
            setupProfile(it)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        mainProfileActivity
            .progressBar
            .visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setupData(friendResponds: FriendResponds) {
        mainViewModel.getFriend(friendResponds.login)
        mainViewModel.getFollower(friendResponds.login)
        mainViewModel.getFollowing(friendResponds.login)
    }

    private fun setupProfile(user: UserResponse) {
        with(user) {
            mainProfileActivity.apply {
                Glide.with(this@MainProfileActivity)
                    .load(avatarUrl)
                    .into(mainProfileActivity.imgMainProfile)
            }
            mainProfileActivity.tvFollower.text = followers.toString()
            mainProfileActivity.tvFollowing.text = following.toString()
            mainProfileActivity.tvMainName.text = login
            mainProfileActivity.tvMainTag.text = name
        }
    }

    companion object {
        const val MAIN_PERSON = "main_person"
        private val TABS_TITLE = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
}
package com.miftah.myinstagramfriendslist.ui.profile

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.databinding.ActivityMainProfileBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterTabLayout

class MainProfileActivity : AppCompatActivity() {

    private lateinit var mainProfileActivity: ActivityMainProfileBinding
    private var friendRespond : FriendRespond? = null
    private val mainViewModel by viewModels<ViewModelProfile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainProfileActivity = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(mainProfileActivity.root)

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        friendRespond = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<FriendRespond>(MAIN_PERSON, FriendRespond::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<FriendRespond>(MAIN_PERSON)
        }

        setupLayout(friendRespond as FriendRespond)

        mainViewModel.userRespond.observe(this) {
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

    private fun setupLayout(friendRespond: FriendRespond) {
        mainViewModel.getFriend(friendRespond.login)
        val selectionAdapter = AdapterTabLayout(this)
        val viewPager = mainProfileActivity.viewPager

        viewPager.adapter = selectionAdapter
        val tabs = mainProfileActivity.tabMenu

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TABS_TITLE[position])
        }.attach()

    }

    private fun setupProfile(user: UserRespond) {
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
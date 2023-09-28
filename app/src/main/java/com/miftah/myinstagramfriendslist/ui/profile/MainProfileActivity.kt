package com.miftah.myinstagramfriendslist.ui.profile

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.databinding.ActivityMainProfileBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterSelectionLayout

class MainProfileActivity : AppCompatActivity() {

    private lateinit var mainProfileActivity: ActivityMainProfileBinding
    private var friendRespond: FriendRespond? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainProfileActivity = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(mainProfileActivity.root)

        friendRespond = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<FriendRespond>(MAIN_PERSON, FriendRespond::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<FriendRespond>(MAIN_PERSON)
        }

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val profileViewModel: ViewModelProfile by viewModels {
            factory
        }

        friendRespond?.let {result ->
            profileViewModel.getUser(result.login).observe(this@MainProfileActivity) { data ->
                data?.let {
                    observableData(data)
                }
            }
        }
    }

    private fun observableData(result : Result<UserRespond>) {
        when (result) {
            is Result.Loading -> mainProfileActivity.progressBar.visibility = View.VISIBLE
            is Result.Error -> {
                mainProfileActivity.progressBar.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Terjadi kesalahan " + result.error,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Result.Success -> {
                mainProfileActivity.progressBar.visibility = View.GONE
                setupProfile(result.data)
                setupLayoutViewPager(result.data)
            }
        }
    }

    private fun setupLayoutViewPager(data : UserRespond) {
        val selectionAdapter = AdapterSelectionLayout(this, data)
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
                tvFollower.text = followers.toString()
                tvFollowing.text = following.toString()
                tvMainName.text = login
                tvMainTag.text = name
            }
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
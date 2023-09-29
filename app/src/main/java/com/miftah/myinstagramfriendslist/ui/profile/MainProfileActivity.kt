package com.miftah.myinstagramfriendslist.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.databinding.ActivityMainProfileBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterSelectionLayout

class MainProfileActivity : AppCompatActivity() {

    private lateinit var mainProfileActivity: ActivityMainProfileBinding
    private val profileViewModel by viewModels<ViewModelProfile>() {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainProfileActivity = ActivityMainProfileBinding.inflate(layoutInflater)
        setContentView(mainProfileActivity.root)

        val personName = intent.getStringExtra(PERSON_NAME) as String

        profileViewModel.getUser(personName).observe(this) { data ->
            data?.let {
                observableData(data)
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
        profileViewModel.isFavUser(user.login).observe(this) {
            if (it) {
                mainProfileActivity.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
                mainProfileActivity.fabAdd.setOnClickListener{
                    profileViewModel.deleteFavPerson(user.login)
                }
            }else {
                mainProfileActivity.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
                mainProfileActivity.fabAdd.setOnClickListener {
                    profileViewModel.insertFavPUser(FavFriend(user.login, user.avatarUrl))
                }
            }
        }
    }

    companion object {
        const val PERSON_NAME = "person_name"
        private val TABS_TITLE = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
}
package com.miftah.myinstagramfriendslist.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.databinding.ActivityMainBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.vmodel.ViewModelMain

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinder: ActivityMainBinding
    private val mainViewModel by viewModels<ViewModelMain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mainViewModel.friendResponds.observe(this) {
            showData(it)
        }

        val layoutManager = LinearLayoutManager(this)
        mainActivityBinder.rvMain.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        mainActivityBinder.rvMain.addItemDecoration(itemDecoration)

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        mainActivityBinder.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showData(listFriend: List<FriendResponds>) {
        val adapter = AdapterFriendCard()
        adapter.submitList(listFriend)
        mainActivityBinder.rvMain.adapter = adapter
    }
}
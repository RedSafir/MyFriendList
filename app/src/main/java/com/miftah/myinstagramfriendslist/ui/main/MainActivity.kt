package com.miftah.myinstagramfriendslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.databinding.ActivityMainBinding
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinder: ActivityMainBinding
    private lateinit var adapter: AdapterFriendCard
    private val mainViewModel by viewModels<ViewModelMain>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinder.root)
        supportActionBar?.hide()

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.getFriendsAll()

        setupRv()

        setupSrc()

        mainViewModel.friendRespond.observe(this) {
            showData(it)
        }

    }

    private fun setupSrc() {
        with(mainActivityBinder) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchBar.text?.let {
                        if (it.isNotEmpty()) {
                            mainViewModel.getFindFriend(searchBar.text.toString())
                        }else{
                            mainViewModel.getFriendsAll()
                        }
                    }
                    searchView.hide()
                    false
                }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        mainActivityBinder
            .progressBar
            .visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showData(listFriend: List<FriendRespond>) {
        adapter.submitList(listFriend)
    }

    private fun setupRv() {
        val layoutManager = LinearLayoutManager(this)
        adapter = AdapterFriendCard()
        mainActivityBinder.rvMain.adapter = adapter
        mainActivityBinder.rvMain.layoutManager = layoutManager
        mainActivityBinder.rvMain.addItemDecoration(
            DividerItemDecoration(this, layoutManager.orientation)
        )   
        adapter.setOnClickCallback(object : AdapterFriendCard.IOnClickListener {
            override fun onClickCard(friendRespondItem: FriendRespond) {
                val moveWithObject = Intent(this@MainActivity, MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.MAIN_PERSON, friendRespondItem)
                startActivity(moveWithObject)
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }

}
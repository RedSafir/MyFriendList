package com.miftah.myinstagramfriendslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.databinding.ActivityMainBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinder: ActivityMainBinding
    private lateinit var adapter: AdapterFriendCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinder.root)
        supportActionBar?.hide()

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val mainViewModel: ViewModelMain by viewModels {
            factory
        }

        setupRv()

        mainViewModel.friendAll.observe(this) { result ->
            result?.let {
                when (it) {
                    is Result.Loading -> mainActivityBinder.progressBar.visibility = View.VISIBLE
                    is Result.Error -> {
                        mainActivityBinder.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "Terjadi kesalahan " + it.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Result.Success -> {
                        mainActivityBinder.progressBar.visibility = View.GONE
                        adapter.submitList(it.data)
                    }
                }
            }
        }

        with(mainActivityBinder) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    searchBar.text?.let {
                        if (it.isNotEmpty()) {
                            mainViewModel.findFriend(searchBar.text.toString())
                        } else {
                            mainViewModel.getFriendAll()
                        }
                    }
                    searchView.hide()
                    false
                }
        }

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
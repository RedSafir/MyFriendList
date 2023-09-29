package com.miftah.myinstagramfriendslist.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.R
import com.miftah.myinstagramfriendslist.data.remote.response.FavFriend
import com.miftah.myinstagramfriendslist.databinding.ActivityMainBinding
import com.miftah.myinstagramfriendslist.repository.Result
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFriendCard
import com.miftah.myinstagramfriendslist.ui.favorite.FavoriteActivity
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinder: ActivityMainBinding
    private lateinit var adapter: AdapterFriendCard
    private val mainViewModel by viewModels<ViewModelMain> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinder.root)
        supportActionBar?.hide()

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
            searchBar.inflateMenu(R.menu.option_menu)
            searchBar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu1 -> {
                        val moveWithObject = Intent(this@MainActivity, FavoriteActivity::class.java)
                        startActivity(moveWithObject)
                    }
                }
                true
            }
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
            override fun onClickCard(favFriendItem: FavFriend) {
                val moveWithObject = Intent(this@MainActivity, MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.PERSON_NAME, favFriendItem.login)
                startActivity(moveWithObject)
            }
        })
    }

    companion object {
        const val TAG = "MainActivity"
    }

}
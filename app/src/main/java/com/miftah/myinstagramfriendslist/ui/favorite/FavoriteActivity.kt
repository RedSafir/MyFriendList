package com.miftah.myinstagramfriendslist.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miftah.myinstagramfriendslist.databinding.ActivityFavoriteBinding
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory
import com.miftah.myinstagramfriendslist.ui.adapter.AdapterFavCard
import com.miftah.myinstagramfriendslist.ui.profile.MainProfileActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var favoriteBinding: ActivityFavoriteBinding
    private lateinit var adapter: AdapterFavCard
    private val favoriteViewModel by viewModels<ViewModelFavorite> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        setupRv()

        favoriteViewModel.getFavPersons().observe(this) {
            adapter.submitList(it)
        }

    }

    private fun setupRv() {
        val layoutManager = LinearLayoutManager(this)
        adapter = AdapterFavCard()
        favoriteBinding.rvFav.adapter = adapter
        favoriteBinding.rvFav.layoutManager = layoutManager
        favoriteBinding.rvFav.addItemDecoration(
            DividerItemDecoration(this, layoutManager.orientation)
        )
        adapter.setOnClickCallback(object : AdapterFavCard.IOnClickListener {
            override fun onClickCard(favFriendItem: com.miftah.myinstagramfriendslist.data.local.entity.FavFriend) {
                val moveWithObject = Intent(this@FavoriteActivity, MainProfileActivity::class.java)
                moveWithObject.putExtra(MainProfileActivity.PERSON_NAME, favFriendItem.name)
                startActivity(moveWithObject)
            }

        })
    }
}
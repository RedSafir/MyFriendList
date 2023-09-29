package com.miftah.myinstagramfriendslist.di

import android.content.Context
import com.miftah.myinstagramfriendslist.data.local.room.FavFriendDatabase
import com.miftah.myinstagramfriendslist.data.remote.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.repository.ProfileRepository

object Injection {
    fun provideRepository(context : Context) : ProfileRepository{
        val apiConfig = ApiConfig.getApiService()
        val dB = FavFriendDatabase.getInstance(context)
        val userDB = dB.userDao()
        return ProfileRepository.getInstance(apiConfig, userDB)
    }
}
package com.miftah.myinstagramfriendslist.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.miftah.myinstagramfriendslist.BuildConfig
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend
import com.miftah.myinstagramfriendslist.data.local.room.FavFriendDao
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.data.remote.retrofit.ApiService

class ProfileRepository(
    private val favFriend: FavFriendDao,
    private val apiService: ApiService
) {

    fun getAllFriend() : LiveData<Result<List<com.miftah.myinstagramfriendslist.data.remote.response.FavFriend>>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getFriends(API_KEY)
            emit(Result.Success(client))
        }catch (e : Exception) {
            Log.d(TAG, "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun findFriend(name : String) : LiveData<Result<List<com.miftah.myinstagramfriendslist.data.remote.response.FavFriend>>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getFindFriend(name, API_KEY).items
            emit(Result.Success(client))
        }catch (e : Exception) {
            Log.d(TAG, "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUser(name : String) : LiveData<Result<UserRespond>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getFriend(name, API_KEY)
            emit(Result.Success(client))
        }catch (e : Exception) {
            Log.d(TAG, "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFollower(name : String) : LiveData<Result<List<com.miftah.myinstagramfriendslist.data.remote.response.FavFriend>>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getFriendFollowers(name, API_KEY)
            emit(Result.Success(client))
        }catch (e : Exception) {
            Log.d(TAG, "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFollowing(name : String) : LiveData<Result<List<com.miftah.myinstagramfriendslist.data.remote.response.FavFriend>>> = liveData {
        emit(Result.Loading)
        try {
            val client = apiService.getFriendFollowings(name, API_KEY)
            emit(Result.Success(client))
        }catch (e : Exception) {
            Log.d(TAG, "getHeadlineNews: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    suspend fun saveFavUser(fav : FavFriend) {
        favFriend.insertUser(fav)
    }

    suspend fun deleteFavUser(name : String){
        favFriend.deleteUser(name)
    }

    fun isUserFav(name : String) = favFriend.isUserFav(name)

    fun getFavPersons() = favFriend.getAll()

    companion object {
        const val API_KEY = BuildConfig.API_KEY
        const val TAG = "Profile_Repository"
        @Volatile
        private var instance: ProfileRepository? = null
        fun getInstance(
            apiService: ApiService,
            favFriend: FavFriendDao,
        ): ProfileRepository =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(favFriend, apiService)
            }.also { instance = it }
    }
}
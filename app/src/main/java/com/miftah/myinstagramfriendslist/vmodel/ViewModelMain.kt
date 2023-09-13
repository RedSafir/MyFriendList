package com.miftah.myinstagramfriendslist.vmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.data.retrofit.FriendList
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.data.retrofit.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMain : ViewModel() {

    // friend data
    private val _userResponse = MutableLiveData<UserResponse>()
    val UserResponse: LiveData<UserResponse> = _userResponse

    // all data friends follower
    private val _friendFollower = MutableLiveData<List<FriendResponds>>()
    val friendFollower: LiveData<List<FriendResponds>> = _friendFollower

    // all data friends following
    private val _friendFollowing = MutableLiveData<List<FriendResponds>>()
    val friendFollowing: LiveData<List<FriendResponds>> = _friendFollowing

    // all data friends
    private val _friendResponds = MutableLiveData<List<FriendResponds>>()
    val friendResponds: LiveData<List<FriendResponds>> = _friendResponds

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /* Nyari semua */
    fun getFriendsAll() {

        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriends()

        client.enqueue(object : Callback<List<FriendResponds>> {
            override fun onResponse(
                call: Call<List<FriendResponds>>,
                response: Response<List<FriendResponds>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendResponds.value = responses
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FriendResponds>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    /* Nyari lewat nama */
    fun getFindFriend(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFindFriend(name)
        client.enqueue(object : Callback<FriendList>{
            override fun onResponse(call: Call<FriendList>, response: Response<FriendList>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendResponds.value = responses.items
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FriendList>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    /* Nyari orang */
    fun getFriend(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriend(name)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _userResponse.value = responses
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getFollower(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriendFollowers(name)
        client.enqueue(object : Callback<List<FriendResponds>> {
            override fun onResponse(
                call: Call<List<FriendResponds>>,
                response: Response<List<FriendResponds>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendFollower.value = responses
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FriendResponds>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun getFollowing(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriendFollowings(name)
        client.enqueue(object : Callback<List<FriendResponds>> {
            override fun onResponse(
                call: Call<List<FriendResponds>>,
                response: Response<List<FriendResponds>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendFollowing.value = responses
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FriendResponds>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    init {
        getFriendsAll()
    }

    companion object {
        const val TAG = "ViewModelMain"
    }
}
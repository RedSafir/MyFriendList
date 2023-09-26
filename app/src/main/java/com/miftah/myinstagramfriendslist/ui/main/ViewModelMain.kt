package com.miftah.myinstagramfriendslist.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.data.remote.response.FriendList
import com.miftah.myinstagramfriendslist.data.remote.response.FriendResponds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelMain : ViewModel() {

    private val _friendResponds = MutableLiveData<List<FriendResponds>>()
    val friendResponds: LiveData<List<FriendResponds>> = _friendResponds

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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

    fun getFindFriend(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFindFriend(name)
        client.enqueue(object : Callback<FriendList> {
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

    companion object {
        const val TAG = "ViewModelMain"
    }
}
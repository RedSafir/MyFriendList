package com.miftah.myinstagramfriendslist.ui.follower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.data.remote.response.FriendResponds
import com.miftah.myinstagramfriendslist.ui.main.ViewModelMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollower : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _friendFollower = MutableLiveData<List<FriendResponds>>()
    val friendFollower: LiveData<List<FriendResponds>> = _friendFollower

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
                    Log.e(ViewModelMain.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FriendResponds>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ViewModelMain.TAG, "onFailure: ${t.message}")
            }

        })
    }
}
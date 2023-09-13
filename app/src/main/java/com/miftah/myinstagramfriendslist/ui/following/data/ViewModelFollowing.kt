package com.miftah.myinstagramfriendslist.ui.following.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.data.retrofit.FriendResponds
import com.miftah.myinstagramfriendslist.ui.main.data.ViewModelMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollowing : ViewModel() {

    // all data friends following
    private val _friendFollowing = MutableLiveData<List<FriendResponds>>()
    val friendFollowing: LiveData<List<FriendResponds>> = _friendFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
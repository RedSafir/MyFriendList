package com.miftah.myinstagramfriendslist.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.ui.main.ViewModelMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFollowing : ViewModel() {

    // all data friends following
    private val _friendFollowing = MutableLiveData<List<FriendRespond>>()
    val friendFollowing: LiveData<List<FriendRespond>> = _friendFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFollowing(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriendFollowings(name)
        client.enqueue(object : Callback<List<FriendRespond>> {
            override fun onResponse(
                call: Call<List<FriendRespond>>,
                response: Response<List<FriendRespond>>
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

            override fun onFailure(call: Call<List<FriendRespond>>, t: Throwable) {
                _isLoading.value = false
                Log.e(ViewModelMain.TAG, "onFailure: ${t.message}")
            }

        })
    }
}
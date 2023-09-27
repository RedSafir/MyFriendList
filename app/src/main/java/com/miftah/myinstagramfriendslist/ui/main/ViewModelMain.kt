package com.miftah.myinstagramfriendslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond

class ViewModelMain : ViewModel() {

    private val _friendRespond = MutableLiveData<List<FriendRespond>>()
    val friendRespond: LiveData<List<FriendRespond>> = _friendRespond

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /*fun getFriendsAll() {

        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriends()

        client.enqueue(object : Callback<List<FriendRespond>> {
            override fun onResponse(
                call: Call<List<FriendRespond>>,
                response: Response<List<FriendRespond>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendRespond.value = responses
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FriendRespond>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }*/

    /*fun getFindFriend(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFindFriend(name)
        client.enqueue(object : Callback<FriendListResponse> {
            override fun onResponse(call: Call<FriendListResponse>, response: Response<FriendListResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _friendRespond.value = responses.items
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FriendListResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }*/

    companion object {
        const val TAG = "ViewModelMain"
    }
}
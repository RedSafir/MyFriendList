package com.miftah.myinstagramfriendslist.ui.profile.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.retrofit.ApiConfig
import com.miftah.myinstagramfriendslist.data.retrofit.UserResponse
import com.miftah.myinstagramfriendslist.ui.main.data.ViewModelMain
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelProfile : ViewModel() {

    private val _userResponse = MutableLiveData<UserResponse>()
    val userResponse: LiveData<UserResponse> = _userResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
                    Log.e(ViewModelMain.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ViewModelMain.TAG, "onFailure: ${t.message}")
            }

        })
    }

}
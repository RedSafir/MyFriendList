package com.miftah.myinstagramfriendslist.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond

class ViewModelProfile : ViewModel() {

    private val _userRespond = MutableLiveData<UserRespond>()
    val userRespond: LiveData<UserRespond> = _userRespond

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /*fun getFriend(name: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFriend(name)
        client.enqueue(object : Callback<UserRespond> {
            override fun onResponse(call: Call<UserRespond>, response: Response<UserRespond>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responses = response.body()
                    if (responses != null) {
                        _userRespond.value = responses
                    }
                } else {
                    Log.e(ViewModelMain.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserRespond>, t: Throwable) {
                _isLoading.value = false
                Log.e(ViewModelMain.TAG, "onFailure: ${t.message}")
            }

        })
    }*/

}
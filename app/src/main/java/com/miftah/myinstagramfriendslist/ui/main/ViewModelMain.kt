package com.miftah.myinstagramfriendslist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.miftah.myinstagramfriendslist.data.remote.response.Friend
import com.miftah.myinstagramfriendslist.repository.ProfileRepository
import com.miftah.myinstagramfriendslist.repository.Result

class ViewModelMain(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _friendAll = MediatorLiveData<Result<List<Friend>>>()
    val friendAll: LiveData<Result<List<Friend>>> by lazy {
        getFriendAll()
        _friendAll
    }

    fun getFriendAll() {
        val result = profileRepository.getAllFriend()
        _friendAll.addSource(result) {
            _friendAll.value = it
        }
    }

    fun findFriend(name: String) {
        val result = profileRepository.findFriend(name)
        _friendAll.addSource(result) {
            _friendAll.value = it
        }
    }

    fun getTheme() = profileRepository.getTheme().asLiveData()

    companion object {
        const val TAG = "ViewModelMain"
    }
}
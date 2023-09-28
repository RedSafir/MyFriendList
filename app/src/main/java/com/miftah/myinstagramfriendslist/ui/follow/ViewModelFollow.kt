package com.miftah.myinstagramfriendslist.ui.follow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.repository.ProfileRepository
import com.miftah.myinstagramfriendslist.repository.Result

class ViewModelFollow(private val profileRepository: ProfileRepository) : ViewModel() {

    fun getFollower(name : String) : LiveData<Result<List<FriendRespond>>> = profileRepository.getFollower(name)

    fun getFollowing(name : String) : LiveData<Result<List<FriendRespond>>> = profileRepository.getFollowing(name)
}
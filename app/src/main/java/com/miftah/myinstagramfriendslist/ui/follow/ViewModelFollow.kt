package com.miftah.myinstagramfriendslist.ui.follow

import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.repository.ProfileRepository

class ViewModelFollow(private val profileRepository: ProfileRepository) : ViewModel() {

    fun getFollower(name : String)  = profileRepository.getFollower(name)

    fun getFollowing(name : String)  = profileRepository.getFollowing(name)
}
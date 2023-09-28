package com.miftah.myinstagramfriendslist.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import com.miftah.myinstagramfriendslist.repository.ProfileRepository
import com.miftah.myinstagramfriendslist.repository.Result

class ViewModelProfile(private val profileRepository: ProfileRepository) : ViewModel() {
    fun getUser(name : String) : LiveData<Result<UserRespond>> = profileRepository.getUser(name)
}
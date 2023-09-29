package com.miftah.myinstagramfriendslist.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend
import com.miftah.myinstagramfriendslist.repository.ProfileRepository
import kotlinx.coroutines.launch

class ViewModelProfile(private val profileRepository: ProfileRepository) : ViewModel() {

    fun getUser(name: String) = profileRepository.getUser(name)

    fun isFavUser(name: String) = profileRepository.isUserFav(name)

    fun insertFavPUser(friend: FavFriend) {
        viewModelScope.launch {
            profileRepository.saveFavUser(friend)
        }
    }

    fun deleteFavPerson(name: String) {
        viewModelScope.launch {
            profileRepository.deleteFavUser(name)
        }
    }
}
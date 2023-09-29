package com.miftah.myinstagramfriendslist.ui.favorite

import androidx.lifecycle.ViewModel
import com.miftah.myinstagramfriendslist.repository.ProfileRepository

class ViewModelFavorite(private val profileRepository: ProfileRepository) : ViewModel() {
    fun getFavPersons() = profileRepository.getFavPersons()

}
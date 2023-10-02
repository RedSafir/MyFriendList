package com.miftah.myinstagramfriendslist.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.miftah.myinstagramfriendslist.repository.ProfileRepository
import kotlinx.coroutines.launch

class ViewModelSetting(private val profileRepository: ProfileRepository) : ViewModel() {

    fun getTheme() = profileRepository.getTheme().asLiveData()

    fun saveTheme(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            profileRepository.saveTheme(isDarkModeActive)
        }
    }
}
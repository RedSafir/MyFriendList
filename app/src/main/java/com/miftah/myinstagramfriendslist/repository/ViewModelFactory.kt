package com.miftah.myinstagramfriendslist.repository

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.miftah.myinstagramfriendslist.di.Injection
import com.miftah.myinstagramfriendslist.ui.follow.ViewModelFollow
import com.miftah.myinstagramfriendslist.ui.main.ViewModelMain
import com.miftah.myinstagramfriendslist.ui.profile.ViewModelProfile

class ViewModelFactory(private val newsRepository: ProfileRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMain::class.java)) {
            return ViewModelMain(newsRepository) as T
        } else if (modelClass.isAssignableFrom(ViewModelProfile::class.java)) {
            return ViewModelProfile(newsRepository) as T
        } else if (modelClass.isAssignableFrom(ViewModelFollow::class.java)) {
            return ViewModelFollow(newsRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}
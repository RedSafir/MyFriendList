package com.miftah.myinstagramfriendslist.ui.setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.miftah.myinstagramfriendslist.databinding.ActivitySettingPreferenceBinding
import com.miftah.myinstagramfriendslist.repository.ViewModelFactory

class SettingPreferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingPreferenceBinding
    private val profileViewModel by viewModels<ViewModelSetting>() {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = binding.switchTheme

        profileViewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _, isCheked ->
            profileViewModel.saveTheme(isCheked)
        }
    }
}
package com.miftah.myinstagramfriendslist.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavFriend(

	@field:SerializedName("following_url") //
	val followingUrl: String,

	@field:SerializedName("login") //
	val login: String,

	@field:SerializedName("followers_url") //
	val followersUrl: String,

	@field:SerializedName("avatar_url") //
	val avatarUrl: String,
) : Parcelable

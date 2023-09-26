package com.miftah.myinstagramfriendslist.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class FriendList(

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean,

	@field:SerializedName("items")
	val items: List<FriendResponds>
)

@Parcelize
data class FriendResponds(

	@field:SerializedName("following_url") //
	val followingUrl: String,

	@field:SerializedName("login") //
	val login: String,

	@field:SerializedName("followers_url") //
	val followersUrl: String,

	@field:SerializedName("avatar_url") //
	val avatarUrl: String,

) : Parcelable

data class UserResponse(

	@field:SerializedName("following_url") //
	val followingUrl: String,

	@field:SerializedName("login") //
	val login: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("followers_url") //
	val followersUrl: String,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String,

	@field:SerializedName("avatar_url") //
	val avatarUrl: String,

	@field:SerializedName("following") //
	val following: Int,

	@field:SerializedName("followers") //
	val followers: Int,

	@field:SerializedName("name") //
	val name: String,
)
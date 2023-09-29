package com.miftah.myinstagramfriendslist.data.remote.response

import com.google.gson.annotations.SerializedName

data class FriendListResponse(

    @field:SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @field:SerializedName("items")
    val items: List<FavFriend>
)


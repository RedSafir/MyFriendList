package com.miftah.myinstagramfriendslist.data.remote.retrofit

import com.miftah.myinstagramfriendslist.data.remote.response.FriendList
import com.miftah.myinstagramfriendslist.data.remote.response.FriendResponds
import com.miftah.myinstagramfriendslist.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getFriends() : Call<List<FriendResponds>>

    @GET("search/users")
    fun getFindFriend(
        @Query("q") name : String
    ) : Call<FriendList>

    @GET("users/{name}")
    fun getFriend(
        @Path("name") name : String
    ) : Call<UserResponse>

    @GET("users/{name}/followers")
    fun getFriendFollowers(
        @Path("name") name : String
    ) : Call<List<FriendResponds>>

    @GET("users/{name}/following")
    fun getFriendFollowings(
        @Path("name") name : String
    ) : Call<List<FriendResponds>>
}
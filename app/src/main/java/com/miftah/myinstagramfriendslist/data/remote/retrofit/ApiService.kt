package com.miftah.myinstagramfriendslist.data.remote.retrofit

import com.miftah.myinstagramfriendslist.data.remote.response.FriendListResponse
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getFriends(
        @Query("apiKey") apiKey: String
    ): List<FriendRespond>

    @GET("search/users")
    suspend fun getFindFriend(
        @Query("q") name: String,
        @Query("apiKey") apiKey: String
    ): FriendListResponse

    @GET("users/{name}")
    suspend fun getFriend(
        @Path("name") name: String,
        @Query("apiKey") apiKey: String
    ): UserRespond

    @GET("users/{name}/followers")
    suspend fun getFriendFollowers(
        @Path("name") name: String,
        @Query("apiKey") apiKey: String
    ): List<FriendRespond>

    @GET("users/{name}/following")
    suspend fun getFriendFollowings(
        @Path("name") name: String,
        @Query("apiKey") apiKey: String
    ): List<FriendRespond>
}
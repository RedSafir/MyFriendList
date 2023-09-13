package com.miftah.myinstagramfriendslist.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val TOKEN = "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"
interface ApiService {

    @GET("users")
    @Headers("Authorization: token $TOKEN")
    fun getFriends() : Call<List<FriendResponds>>

    @GET("search/users")
    @Headers("Authorization: token $TOKEN")
    fun getFindFriend(
        @Query("q") name : String
    ) : Call<FriendList>

    @GET("users/{name}")
    @Headers("Authorization: token $TOKEN")
    fun getFriend(
        @Path("name") name : String
    ) : Call<UserResponse>

    @GET("users/{name}/followers")
    @Headers("Authorization: token $TOKEN")
    fun getFriendFollowers(
        @Path("name") name : String
    ) : Call<List<FriendResponds>>

    @GET("users/{name}/following")
    @Headers("Authorization: token $TOKEN")
    fun getFriendFollowings(
        @Path("name") name : String
    ) : Call<List<FriendResponds>>
}
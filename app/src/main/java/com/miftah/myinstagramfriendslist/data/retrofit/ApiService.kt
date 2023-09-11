package com.miftah.myinstagramfriendslist.data.retrofit

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("users")
    @Headers("Authorization: token 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")
    fun getFriends() : Call<List<FriendResponds>>

    @GET("users/users?q={name}")
    @Headers("Authorization: token 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")
    fun getFindFriend(
        @Field("name") name : String
    ) : Call<List<FriendResponds>>

    @GET("users/{name}")
    @Headers("Authorization: token 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")
    fun getFriend(
        @Field("name") name : String
    ) : Call<FriendResponds>

    @GET("users/{name}/followers")
    @Headers("Authorization: token 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")
    fun getFriendFollowers(
        @Field("name") name : String
    ) : Call<List<FriendResponds>>

    @GET("users/{name}/followings")
    @Headers("Authorization: token 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")
    fun getFriendFollowings(
        @Field("name") name : String
    ) : Call<List<FriendResponds>>
}
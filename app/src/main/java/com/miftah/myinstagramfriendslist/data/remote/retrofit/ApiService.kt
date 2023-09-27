package com.miftah.myinstagramfriendslist.data.remote.retrofit

import com.miftah.myinstagramfriendslist.data.remote.response.FriendListResponse
import com.miftah.myinstagramfriendslist.data.remote.response.FriendRespond
import com.miftah.myinstagramfriendslist.data.remote.response.UserRespond
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getFriends() : Call<List<FriendRespond>>

    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getFindFriend(
        @Query("q") name : String
    ) : Call<FriendListResponse>

    @Headers("Authorization: token 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")
    @GET("users/{name}")
    fun getFriend(
        @Path("name") name : String
    ) : Call<UserRespond>

    @Headers("Authorization: token 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")
    @GET("users/{name}/followers")
    fun getFriendFollowers(
        @Path("name") name : String
    ) : Call<List<FriendRespond>>

    @Headers("Authorization: token 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")
    @GET("users/{name}/following")
    fun getFriendFollowings(
        @Path("name") name : String
    ) : Call<List<FriendRespond>>
}
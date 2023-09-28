package com.miftah.myinstagramfriendslist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(

    @field:PrimaryKey
    @field:ColumnInfo(name = "name")
    val name : String,

    @field:ColumnInfo(name = "img")
    val img : String,

    @field:ColumnInfo(name = "followersUrl")
    val followersUrl : String,

    @field:ColumnInfo(name = "followingUrl")
    val followingUrl : String,

    @field:ColumnInfo(name = "followers")
    val followers : Int,

    @field:ColumnInfo(name = "following")
    val following : Int,
)

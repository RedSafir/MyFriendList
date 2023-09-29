package com.miftah.myinstagramfriendslist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavFriend(

    @field:PrimaryKey(autoGenerate = false)
    @field:ColumnInfo(name = "name")
    val name : String,

    @field:ColumnInfo(name = "img")
    val img : String,
)

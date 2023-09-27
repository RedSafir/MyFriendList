package com.miftah.myinstagramfriendslist.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "img")
    val img : String,

    @ColumnInfo(name = "followers")
    val followers : String,

    @ColumnInfo(name = "following")
    val following : String,

    @ColumnInfo(name = "favorite")
    val isFav : Boolean
)

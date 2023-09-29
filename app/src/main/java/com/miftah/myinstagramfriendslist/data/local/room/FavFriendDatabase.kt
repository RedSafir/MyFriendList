package com.miftah.myinstagramfriendslist.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend

@Database(entities = [FavFriend::class], version = 1)
abstract class FavFriendDatabase : RoomDatabase() {
    abstract fun userDao() :FavFriendDao

    companion object{
        @Volatile
        private var instance : FavFriendDatabase? = null

        @JvmStatic
        fun getInstance(context : Context): FavFriendDatabase {
            return instance?: synchronized(FavFriendDatabase::class.java) {
                instance?: Room.databaseBuilder(
                    context.applicationContext,
                    FavFriendDatabase::class.java,
                    "favFriend.db"
                ).build()
            }
        }
    }
}
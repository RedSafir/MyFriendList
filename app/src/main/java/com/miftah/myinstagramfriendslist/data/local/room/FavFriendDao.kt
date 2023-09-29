package com.miftah.myinstagramfriendslist.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miftah.myinstagramfriendslist.data.local.entity.FavFriend

@Dao
interface FavFriendDao {
    @Query("SELECT * FROM FavFriend ORDER BY name DESC")
    fun getAll() : LiveData<List<FavFriend>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(favFriend : FavFriend)

    @Query("DELETE FROM FavFriend WHERE name = :name")
    suspend fun deleteUser(name : String)

    @Query("SELECT EXISTS(SELECT * FROM FavFriend WHERE name = :name)")
    fun isUserFav(name: String): LiveData<Boolean>
}
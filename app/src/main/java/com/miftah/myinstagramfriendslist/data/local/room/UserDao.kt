package com.miftah.myinstagramfriendslist.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.miftah.myinstagramfriendslist.data.local.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY name DESC")
    fun getAll() : LiveData<List<User>>

    @Query("SELECT * FROM User WHERE favorite = 1")
    fun getFav() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser()

    @Update
    fun updateFav(fav : User)

    @Query("DELETE FROM user WHERE favorite = 0")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM User WHERE name = :name AND favorite = 1)")
    fun isFavUser(name : String) : Boolean
}
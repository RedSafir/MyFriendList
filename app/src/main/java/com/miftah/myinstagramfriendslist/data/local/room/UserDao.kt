package com.miftah.myinstagramfriendslist.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miftah.myinstagramfriendslist.data.local.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User ORDER BY name DESC")
    fun getAll() : LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user : User)

    @Query("DELETE FROM user WHERE name = :name")
    fun deleteUser(name : String)
}
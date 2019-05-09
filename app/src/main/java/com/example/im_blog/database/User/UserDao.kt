package com.example.im_blog.database.User

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao{
    @Query("SELECT * FROM user WHERE userid=:id")
    fun query(id:Long):LiveData<List<User>>
}
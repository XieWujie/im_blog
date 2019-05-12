package com.example.im_blog.database.passage

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PassageDao{

    @Query("SELECT * FROM Passage ORDER BY id DESC")
    fun query():DataSource.Factory<Int,Passage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(passages:List<Passage>)


}
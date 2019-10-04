package com.example.im_blog.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.im_blog.database.User.User
import com.example.im_blog.database.User.UserDao
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.database.passage.PassageDao


@Database(entities = [Passage::class,User::class],version = 2)
abstract class AppDatabase: RoomDatabase(){

    abstract fun getPassage():PassageDao

    abstract fun getUser():UserDao

    companion object {

        @Volatile
        private var instance:AppDatabase? = null

        fun getInstance(context: Context):AppDatabase{
            return instance?: synchronized(this){
                instance?: buildInstance(context).also { instance = it }
            }
        }
        private fun buildInstance(context: Context):AppDatabase{
            return Room.databaseBuilder(context,AppDatabase::class.java, "im_blog")
                .addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("AppDb","database created")
                    }
                }).build()
        }
    }
}
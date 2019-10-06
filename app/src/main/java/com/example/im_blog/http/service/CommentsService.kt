package com.example.im_blog.http.service

import com.example.im_blog.http.entities.Comments
import com.example.im_blog.http.gson.UserInfoCount
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsService {


    @GET("/2/comments/show.json")
    fun fetchComments(
        @Query("access_token")token:String,
        @Query("id")id:Long,
        @Query("page")page:Int,
        @Query("count")count: Int):Flowable<Comments>
}
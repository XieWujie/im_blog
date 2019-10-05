package com.example.im_blog.http.service

import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsService {


    @GET("/2/comments/show.json")
    fun fetchComments(
        @Query("access_token")token:String,
        @Query("id")id:Long,
        @Query("max_id")max_id:Long)
}
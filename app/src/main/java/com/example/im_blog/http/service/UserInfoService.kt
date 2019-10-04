package com.example.im_blog.http.service

import com.example.im_blog.http.entities.UserInfo
import com.example.im_blog.http.gson.UserInfoCount
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoService {
    @GET("/2/users/show.json")
    fun fetchInfo(@Query("access_token")token:String,@Query("uid")uid:String):Flowable<UserInfo>

    @GET("2/users/counts.json")
    fun fetchCount(@Query("access_token")token:String,@Query("uids")uid:String):Flowable<List<UserInfoCount>>
}
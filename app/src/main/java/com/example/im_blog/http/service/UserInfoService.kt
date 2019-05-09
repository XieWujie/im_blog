package com.example.im_blog.http.service

import com.example.im_blog.http.entities.UserInfo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoService {
    @GET("/2/users/show.json")
    fun fetchInfo(@Query("access_token")token:String,@Query("uid")uid:String):Flowable<UserInfo>
}
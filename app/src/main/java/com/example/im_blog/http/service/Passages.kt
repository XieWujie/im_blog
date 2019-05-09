package com.example.im_blog.http.service

import com.example.im_blog.http.entities.Passages
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface PassagesService{

    @GET("/2/statuses/home_timeline.json")
    fun fetchPassages(
        @Query("access_token")token:String,
        @Query("since_id")since:Long = 0,
        @Query("max_id")max:Long = 0,
        @Query("count")count:Int = 20,
        @Query("page")page:Int = 1,
        @Query("base_app")base_app:Int = 0,
        @Query("feature")feature:Int = 0
    ):Flowable<Passages>

}
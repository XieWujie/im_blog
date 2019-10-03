package com.example.im_blog.repository

import android.util.Log
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.http.service.PassagesService
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PassageListRemoteDataSource (val service: PassagesService):RemoteDataSource{

    fun getByTime(from:Long = 0,last:Long = 0):Flowable<List<Passage>>{
       return service
            .fetchPassages(UserManage.token,from,last)
            .subscribeOn(Schedulers.io())
            .filter { it.total_number > 0 }
            .map { it.statuses }
    }
}
package com.example.im_blog.repository.passsages

import com.example.im_blog.database.passage.Passage
import com.example.im_blog.http.service.PassagesService
import com.example.im_blog.repository.RemoteDataSource
import com.example.im_blog.repository.UserManage
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class PassageListRemoteDataSource (val service: PassagesService):
    RemoteDataSource {

    fun getByTime(from:Long = 0,last:Long = 0):Flowable<List<Passage>>{
       return service
            .fetchPassages(UserManage.token,from,last)
            .subscribeOn(Schedulers.io())
            .filter { it.total_number > 0 }
            .map { it.statuses }
    }

    fun fetchUserPassages(uid:String,last: Long):Flowable<List<Passage>>{
        return service
            .fetchUserPassages(token = UserManage.token,uid = uid,max = last)
            .subscribeOn(Schedulers.io())
            .map {
                it.statuses
            }
    }
}
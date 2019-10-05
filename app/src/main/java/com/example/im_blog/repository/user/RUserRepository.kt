package com.example.im_blog.repository.user

import com.example.im_blog.http.entities.UserInfo
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.http.service.UserInfoService
import com.example.im_blog.repository.RemoteDataSource
import com.example.im_blog.repository.UserManage
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class RUserRepository(private val service:UserInfoService) : RemoteDataSource {


    fun fetchUserInfo():Flowable<UserInfo> = service
        .fetchInfo(UserManage.token,UserManage.uid.toString())
        .subscribeOn(Schedulers.io())

    fun fetchUserInfoCount() = service
        .fetchCount(UserManage.token,UserManage.uid.toString())
        .subscribeOn(Schedulers.io())
        .map { it[0] }
}
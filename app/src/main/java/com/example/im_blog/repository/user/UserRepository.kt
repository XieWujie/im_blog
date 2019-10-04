package com.example.im_blog.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.im_blog.ext.int
import com.example.im_blog.ext.string
import com.example.im_blog.http.entities.UserInfo
import com.example.im_blog.http.gson.UserInfoCount
import com.example.im_blog.repository.BothRepository
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function

class UserRepository(local: LUserRepository, remote: RUserRepository) :
    BothRepository<LUserRepository, RUserRepository>(local, remote) {


    fun fetchMine(): Flowable<Mine> {
        return Flowable
            .zip(remote.fetchUserInfo(), remote.fetchUserInfoCount(), BiFunction<UserInfo,UserInfoCount,Mine> { info, count ->
                Mine(
                    count.friends_count,
                    count.followers_count,
                    count.statuses_count,
                    info.avatar_large,
                    info.description,
                    info.name
                )
            })
            .onErrorResumeNext(Function {
                Flowable.just(Mine(
                    local.friends_count, local.flowers_count, local.statues_count,
                    local.avatar ?: "", local.description ?: "", local.name ?: ""
                ))
            })
            .doOnNext {
                local.apply {
                    avatar = it.avatar
                    friends_count = it.friends_count
                    flowers_count = it.flowers_count
                    description = it.description
                    name = it.name
                    statues_count = it.statues_count
                }
            }

    }
}

    data class Mine(
        val friends_count: Int = 0,
        val flowers_count: Int = 0,
        val statues_count: Int = 0,
        val avatar: String = "",
        val description: String = "",
        val name: String = ""
    )
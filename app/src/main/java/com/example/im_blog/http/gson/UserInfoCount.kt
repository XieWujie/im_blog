package com.example.im_blog.http.gson

data class UserInfoCount(
    val followers_count: Int,
    val friends_count: Int,
    val id: Long,
    val pagefriends_count: Int,
    val private_friends_count: Int,
    val statuses_count: Int
)
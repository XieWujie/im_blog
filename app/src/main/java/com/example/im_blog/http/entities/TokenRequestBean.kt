package com.example.im_blog.http.entities

data class TokenRequestEntity(
    val access_token: String,
    val expires_in: Int,
    val isRealName: String,
    val remind_in: String,
    val uid: String
)
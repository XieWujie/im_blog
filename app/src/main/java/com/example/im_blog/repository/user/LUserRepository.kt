package com.example.im_blog.repository.user

import android.content.SharedPreferences
import com.example.im_blog.ext.boolean
import com.example.im_blog.ext.int
import com.example.im_blog.ext.string
import com.example.im_blog.repository.LocalDataSource

class LUserRepository(preferences: SharedPreferences) :
    LocalDataSource {

    var token by preferences.string("token",defValue = "")
    var autoLogin by preferences.boolean(key = "autoLogin",defValue = true)
    var uid by preferences.string("uid",defValue = "")

    var friends_count by preferences.int()
    var flowers_count by preferences.int()
    var statues_count by preferences.int()

    var avatar by preferences.string()
    var description by preferences.string()
    var name by preferences.string()
}
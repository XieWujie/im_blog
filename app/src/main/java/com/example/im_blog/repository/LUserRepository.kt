package com.example.im_blog.repository

import android.content.SharedPreferences
import com.example.im_blog.ext.boolean
import com.example.im_blog.ext.string

class LUserRepository(preferences: SharedPreferences) :LocalDataSource{

    var token by preferences.string("token",defValue = "")
    var autoLogin by preferences.boolean(key = "autoLogin",defValue = true)
    var uid by preferences.string("uid",defValue = "")

}
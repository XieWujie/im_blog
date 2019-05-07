package com.example.im_blog.di

import android.content.Context
import android.content.SharedPreferences
import com.example.im_blog.base.App
import com.example.im_blog.repository.LUserRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val TAG = "repository_module_tag"
private const val MY_SHARE = "my_share"
val repoKodeinModule = Kodein.Module(TAG){
    bind<SharedPreferences>(MY_SHARE) with singleton { App.INSTANCE.getSharedPreferences("im_blog",Context.MODE_PRIVATE) }
    bind<LUserRepository>() with singleton {
        LUserRepository(
            instance(MY_SHARE)
        )
    }
}
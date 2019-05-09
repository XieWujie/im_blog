package com.example.im_blog.di

import com.example.im_blog.base.App
import com.example.im_blog.database.AppDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

private const val DATABASE_MODULE_TAG = "database_module_tag"
val databaseModule = Kodein.Module(DATABASE_MODULE_TAG){
    bind<AppDatabase>() with singleton { AppDatabase.getInstance(instance<App>()) }
}
package com.example.im_blog.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein

open class BaseActivity :AppCompatActivity(){
    protected val parent by closestKodein()
    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)
    }
}
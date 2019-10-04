package com.example.im_blog.base

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.im_blog.di.databaseModule
import com.example.im_blog.di.httpClientModule
import com.example.im_blog.di.repoKodeinModule
import com.example.im_blog.di.service_module
import io.reactivex.plugins.RxJavaPlugins
import org.kodein.di.Kodein
import org.kodein.di.Kodein.Companion.lazy
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

open class App : Application(), KodeinAware {

    override val kodein: Kodein = lazy {
        bind<App>() with singleton { this@App }
        import(androidXModule(this@App))
        import(repoKodeinModule)
        import(httpClientModule)
        import(service_module)
        import(databaseModule)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        RxJavaPlugins.setErrorHandler {
           it?.apply { toast { it.message!! } }
            it?.printStackTrace()
        }
    }

    companion object {
        lateinit var INSTANCE: App
    }
}


inline fun toast(crossinline value: () -> String): Unit =
    App.INSTANCE.toast(value)

inline fun Context.toast(crossinline value: () -> String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, value(), Toast.LENGTH_SHORT).show()
    }
}
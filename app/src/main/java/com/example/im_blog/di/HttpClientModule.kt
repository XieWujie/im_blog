package com.example.im_blog.di

import com.example.im_blog.http.BasicAuthIntercept
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_CLIENT_MODUEL = "http_client_module"
private  const val  BASE_URL = "https://api.weibo.com"
private const val AUTH_INTERCEPT = "authorization_intercept"

val httpClientModule = Kodein.Module(HTTP_CLIENT_MODUEL){
    bind<Retrofit.Builder>() with provider { Retrofit.Builder() }
    bind<OkHttpClient.Builder>() with provider { OkHttpClient.Builder() }
    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BASE_URL)
            .client(instance())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    bind<Interceptor>(AUTH_INTERCEPT) with singleton {
        BasicAuthIntercept(
            instance()
        )
    }
    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .connectTimeout(8000,TimeUnit.MILLISECONDS)
            .readTimeout(8000,TimeUnit.MILLISECONDS)
            .addInterceptor(instance(AUTH_INTERCEPT))
            .build()
    }
    bind<Gson>() with singleton { Gson() }
}
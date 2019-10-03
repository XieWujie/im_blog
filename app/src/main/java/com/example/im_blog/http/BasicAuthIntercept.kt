package com.example.im_blog.http

import android.util.Log
import com.example.im_blog.repository.LUserRepository
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthIntercept(private val repos:LUserRepository):Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val token  = repos.token
        var request = chain.request()
        val url = request.url().toString().replace("%3A", ":").replace("%2F", "/")
        when(token){
            null->  request = request.newBuilder()
                .url(url)
                .build()
            else->  request = request.newBuilder()
                .addHeader("Authorization",token)
                .url(url)
                .build()
        }
        Log.d("url-",url)
        Log.d("request",request.headers().toString())
        return chain.proceed(request)
    }
}
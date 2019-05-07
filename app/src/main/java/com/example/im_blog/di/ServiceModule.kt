package com.example.im_blog.di

import com.example.im_blog.http.service.TokenService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

private val SERVICE_MODULE = "service_module"

val service_module = Kodein.Module(SERVICE_MODULE){
    bind<TokenService>() with provider {
        instance<Retrofit>().create(TokenService::class.java)
    }
}
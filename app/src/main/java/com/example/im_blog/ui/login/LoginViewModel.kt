package com.example.im_blog.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.comment.SingletonHolderSingleArg
import com.example.im_blog.ext.toReactiveStream
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.repository.LoginRepository
import com.example.im_blog.utilies.REQUEST_CODE_URL
import com.uber.autodispose.lifecycle.autoDisposable

class LoginViewModel internal constructor(private val resp:LoginRepository):AutoDisposeViewModel(){

    val autoLogin = MutableLiveData<Boolean>()
    val webViewClient = MyWebViewClient()
    private val code = webViewClient.infoCode
    private val err = MutableLiveData<Throwable>()
    val loadUrl = MutableLiveData<String>()

    init {

        resp.local.fetchAutoLoginEvent()
            .filter {it.apply { if (!this){loadUrl.postValue(REQUEST_CODE_URL)} }}
            .autoDisposable(this)
            .subscribe{ autoLogin.postValue(true) }

        code.toReactiveStream()
            .flatMap { resp.login(it) }
            .compose(globalHandleError())
            .doOnNext {
                resp.local.save(it)
                Log.d("token",it.access_token)
            }
            .doOnError {
                err.postValue(it)
            }
            .autoDisposable(this)
            .subscribe{ autoLogin.postValue(true) }

    }
}

class LoginModelFactory (private val resp: LoginRepository):ViewModelProvider.NewInstanceFactory(){

    @Suppress( "UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(resp) as T
    }
    companion object:SingletonHolderSingleArg<LoginModelFactory,LoginRepository>(::LoginModelFactory)
}
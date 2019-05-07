package com.example.im_blog.ui.login

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.MutableLiveData
import com.example.im_blog.comment.LoadState

class MyWebViewClient :WebViewClient(){

    val loadState = MutableLiveData<LoadState>()
    val infoCode = MutableLiveData<String>()
    private val targetUrl = "https://api.weibo.com/oauth2/default.html?code="

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        loadState.postValue(LoadState.loading)
        if (view == null){
            return
        }else{
            pageEvent(view)
        }
    }

    private fun pageEvent(view: WebView){
        val url = view.url
        Log.d(TAG,url)
        if (url.contains(targetUrl)){
            val index = url.lastIndexOf("=")
            val code = url.substring(index+1,url.length)
            Log.d(TAG,code)
            infoCode.postValue(code)
        }
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        loadState.postValue(LoadState.succeed)
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        loadState.postValue(LoadState.error(Exception("加载失败")))
    }
}

private const val TAG = "MyWebViewClient"
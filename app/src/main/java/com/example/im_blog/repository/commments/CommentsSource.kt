package com.example.im_blog.repository.commments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.http.entities.Comment
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.http.service.CommentsService
import com.example.im_blog.repository.UserManage
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.autoDisposable
import io.reactivex.schedulers.Schedulers

class CommentsSource(private val  service:CommentsService,
                     private val id:Long,
                     private val scopeProvider: LifecycleScopeProvider<AutoDisposeViewModel.ViewModelEvent>,
                     private val error:MutableLiveData<Throwable>) :PageKeyedDataSource<Int,Comment>(){


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Comment>
    ) {
        load(1).subscribe(
            {
                callback.onResult(it.comments,null,2)
            },
            {
                it.printStackTrace()
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        load(params.key).subscribe(
            {callback.onResult(it.comments,params.key+1)},
            {it.printStackTrace()}
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
//        load(params.key).subscribe(
//            {callback.onResult(it.comments,if (params.key-1<1) 1 else params.key-1)},
//            {it.printStackTrace()}
//        )
    }

    fun load(page:Int,count: Int = 20) =
        service.fetchComments(UserManage.token,id,page,count)
            .subscribeOn(Schedulers.io())
            .compose(globalHandleError())
            .doOnError { error.postValue(it) }
            .autoDisposable(scopeProvider)

}
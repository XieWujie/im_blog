package com.example.im_blog.repository.commments

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.http.entities.Comment
import com.example.im_blog.http.entities.Comments
import com.example.im_blog.http.service.CommentsService
import com.uber.autodispose.lifecycle.LifecycleScopeProvider

class CommentsSourceFactory (private val service:CommentsService,
                             private val id:Long,
                             private val scopeProvider: LifecycleScopeProvider<AutoDisposeViewModel.ViewModelEvent>,
                             private val error:MutableLiveData<Throwable>):DataSource.Factory<Int,Comment>(){

    lateinit var source: CommentsSource
    override fun create(): DataSource<Int, Comment> {
        source = CommentsSource(service,id,scopeProvider,error)
        return source
    }

}
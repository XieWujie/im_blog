package com.example.im_blog.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.http.entities.Comment
import com.example.im_blog.http.service.CommentsService
import com.example.im_blog.repository.commments.CommentsSourceFactory

class CommentsViewModel(private val commentsService: CommentsService) :AutoDisposeViewModel(){

    val error = MutableLiveData<Throwable>()

    fun fetchComments(id:Long) :LiveData<PagedList<Comment>>{
        val factory = CommentsSourceFactory(commentsService,id,this,error)
        return factory.toLiveData(20)
    }
}


class CommentsModelFactory(private val service: CommentsService):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CommentsViewModel(service) as T
    }
}
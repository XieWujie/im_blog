package com.example.im_blog.ui.me

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.repository.user.Mine
import com.example.im_blog.repository.user.UserRepository
import com.uber.autodispose.lifecycle.autoDisposable

class MeViewModel internal constructor(private val repository:UserRepository) :AutoDisposeViewModel(){

    val mine = MutableLiveData<Mine>()

    fun fetchMine() = repository
        .fetchMine()
        .compose(globalHandleError())
        .autoDisposable(this)
        .subscribe(
            {mine.postValue(it)},{it.printStackTrace()}
        )
}

class MeModelFactory(private val repository: UserRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MeViewModel(repository) as T
    }
}
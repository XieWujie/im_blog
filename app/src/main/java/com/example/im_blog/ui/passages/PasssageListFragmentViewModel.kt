package com.example.im_blog.ui.passages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.im_blog.base.AutoDisposeViewModel
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.repository.passsages.PassageListRepository

class MainFragmentViewModel internal constructor(private val resp: PassageListRepository):AutoDisposeViewModel(){

    private val boundaryCallback = PassagesBoundaryCallback(resp,this)
    val err = boundaryCallback.error
    val isLoading = boundaryCallback.isLoading
    private val fresh = boundaryCallback::fresh
    fun passages(passage_type:Int):LiveData<PagedList<Passage>>{
        boundaryCallback.passageType = passage_type
       return resp.local.query(passage_type).toLiveData(20,null,boundaryCallback)
    }
    fun fresh() = fresh.invoke()
}



class MainFragmentViewModelFactory(private val resp: PassageListRepository):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(resp) as T
    }
}
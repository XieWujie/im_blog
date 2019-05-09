package com.example.im_blog.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.repository.PassageListRepository
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.autoDisposable
import io.reactivex.Flowable

class PassagesBoundaryCallback(private val repository: PassageListRepository,private val provider: LifecycleScopeProvider<*>) :PagedList.BoundaryCallback<Passage>(){

    private val service = repository.remote
    private val local = repository.local
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()

    override fun onZeroItemsLoaded() {
        handler(service.getByTime())
    }

    override fun onItemAtEndLoaded(itemAtEnd: Passage) {
        handler(service.getByTime(last = itemAtEnd.id))
    }

    override fun onItemAtFrontLoaded(itemAtFront: Passage) {
      handler(service.getByTime(from = itemAtFront.id))
    }

    fun handler(f: Flowable<List<Passage>>){
        isLoading.postValue(true)
        f.compose(globalHandleError())
            .doOnNext {
                Log.d("list-",it.toString())
                local.insert(it)
            }
            .doOnError {
                error.postValue(it)
                it.printStackTrace()
            }
            .doFinally { isLoading.postValue(false) }
            .autoDisposable(provider)
            .subscribe({},{it?.printStackTrace()})
    }
}
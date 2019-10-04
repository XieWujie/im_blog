package com.example.im_blog.ui.passages

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.repository.passsages.PassageListRepository
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.autoDisposable
import io.reactivex.Flowable

class PassagesBoundaryCallback(
    private val repository: PassageListRepository,
    private val provider: LifecycleScopeProvider<*>
) : PagedList.BoundaryCallback<Passage>() {

    private val service = repository.remote
    private val local = repository.local
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()
    private var fresh = { isLoading.postValue(false) }


    fun fresh() {
        fresh.invoke()
    }

    override fun onZeroItemsLoaded() {
        fresh ={
            isLoading.postValue(true)
            handler(service.getByTime())
        }
        fresh.invoke()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Passage) {
        handler(service.getByTime(last = itemAtEnd.id))
    }

    override fun onItemAtFrontLoaded(itemAtFront: Passage) {

    }

    fun handler(f: Flowable<List<Passage>>) {
        f.compose(globalHandleError())
            .doOnNext {
                local.insert(it)
            }
            .doOnError { error.postValue(it) }
            .doFinally { isLoading.postValue(false) }
            .autoDisposable(provider)
            .subscribe({}, { it?.printStackTrace() })
    }
}
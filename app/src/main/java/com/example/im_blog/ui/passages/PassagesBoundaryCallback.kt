package com.example.im_blog.ui.passages

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.http.globalHandleError
import com.example.im_blog.repository.UserManage
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
    var passageType = Passage.TYPE_FLOWER
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Throwable>()
    private var fresh = { isLoading.postValue(false) }


    fun fresh() {
        fresh.invoke()
    }

    override fun onZeroItemsLoaded() {
        fresh ={
            isLoading.postValue(true)
            dataLoad()
        }
        fresh.invoke()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Passage) {
        dataLoad(itemAtEnd.id)
    }

    fun dataLoad(last:Long = 0){
        when(passageType){
            Passage.TYPE_FLOWER-> handle(service.getByTime(last = last))
            Passage.TYPE_MINE->handle(service.fetchUserPassages(UserManage.uid,last))
        }
    }

    fun handle(f: Flowable<List<Passage>>) {
        f.compose(globalHandleError())
            .doOnNext {
                it.forEach { it.passage_type = passageType }
                local.insert(it)
            }
            .doOnError { error.postValue(it) }
            .doFinally { isLoading.postValue(false) }
            .autoDisposable(provider)
            .subscribe({},{it.printStackTrace()})
    }
}
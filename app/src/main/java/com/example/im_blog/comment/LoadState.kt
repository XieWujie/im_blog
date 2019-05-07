package com.example.im_blog.comment

sealed class LoadState {

    object Loading : LoadState()
    object Succeed : LoadState()
    class Error(val exception: Exception) : LoadState()

    companion object {
        val loading = Loading
        val succeed = Succeed
        fun error(e: Exception) = Error(e)
    }
}
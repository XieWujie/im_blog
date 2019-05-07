package com.example.im_blog.comment

open class SingletonHolderSingleArg<out T,in R>(private val creator:(r:R)->T){

    @Volatile
    private var instance:T ? = null

    fun getInstance(arg:R):T{
        return instance?: synchronized(this){
            instance?:creator.invoke(arg).also {
                instance = it
            }
        }
    }
}
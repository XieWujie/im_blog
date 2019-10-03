package com.example.im_blog.repository

import com.example.im_blog.comment.SingletonHolderSingleArg
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.database.passage.PassageDao

class PassageListLocalDataSource(private val dao: PassageDao) :LocalDataSource{

    fun query() = dao.query()

    fun insert(passages: List<Passage>){
      dao.insert(passages)
    }

    companion object:SingletonHolderSingleArg<PassageListLocalDataSource,PassageDao>(::PassageListLocalDataSource)
}
package com.example.im_blog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.PassageItemBinding

class PassageAdapter :PagedListAdapter<Passage,BaseHolder<Passage>>(PassageDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<Passage> {
        val binging = PassageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PassageHolder(binging)
    }

    override fun onBindViewHolder(holder: BaseHolder<Passage>, position: Int) {
        holder.bind(getItem(position)!!)
    }
}
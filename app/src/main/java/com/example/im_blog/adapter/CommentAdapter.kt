package com.example.im_blog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.im_blog.databinding.CommentItemBinding
import com.example.im_blog.http.entities.Comment
import java.lang.RuntimeException

class CommentAdapter :PagedListAdapter<Comment,CommentHolder>(CommentDifferCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(getItem(position)?:throw RuntimeException("the item is null"))
    }

}


class CommentDifferCallback:DiffUtil.ItemCallback<Comment>(){

    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
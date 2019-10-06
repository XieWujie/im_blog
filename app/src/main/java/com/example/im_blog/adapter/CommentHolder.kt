package com.example.im_blog.adapter

import com.example.im_blog.databinding.CommentItemBinding
import com.example.im_blog.http.entities.Comment

class CommentHolder(private val binding: CommentItemBinding) :BaseHolder<Comment>(binding.root){

    override fun bind(source: Comment) {
        binding.comment = source
    }
}
package com.example.im_blog.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.im_blog.database.passage.Passage

class PassageDiffCallback :DiffUtil.ItemCallback<Passage>(){

    override fun areContentsTheSame(oldItem: Passage, newItem: Passage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Passage, newItem: Passage): Boolean {
        return oldItem == newItem
    }
}
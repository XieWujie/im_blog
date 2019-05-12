package com.example.im_blog.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(view:View):RecyclerView.ViewHolder(view){
    private val context = itemView.context

    abstract fun bind(source:T)

}
package com.example.im_blog.adapter

import androidx.recyclerview.widget.GridLayoutManager
import com.example.im_blog.base.App
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.MainPassageContentItemBinding
import com.google.gson.Gson
import org.kodein.di.generic.instance

class MainPassageHolder (private val binding:MainPassageContentItemBinding):BaseHolder<Passage>(binding.root){

    private val gson: Gson by App.INSTANCE.kodein.instance()
    override fun bind(source: Passage) {
        binding.passage = source
        val pics = getPics(source.pic_urls)
        val span = when (pics.size) {
            1 -> 1
            2 -> 2
            else -> 3
        }
        binding.picRecyclerView.layoutManager = GridLayoutManager(itemView.context, span)
        val adapter = PicAdapter()
        binding.picRecyclerView.adapter = adapter
        adapter.setList(pics.toList())
        adapter.notifyDataSetChanged()
    }

    private fun getPics(pics: String): Array<String> {
        val g = gson.fromJson(pics, Array<String>::class.java)
        return g
    }
}
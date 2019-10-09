package com.example.im_blog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.im_blog.databinding.ImageLayoutBinding

class ImageAdapter(private val itemSelect:(index:Int)->Unit) :RecyclerView.Adapter<ImageAdapter.ImageHolder>(){

    private val mList = ArrayList<String>()

    fun setList(list: List<String>){
        mList.clear()
        mList.addAll(list)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val binding = ImageLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageHolder(binding)
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
       holder.bind(mList[position])
    }


    inner class ImageHolder(private val  binding:ImageLayoutBinding):BaseHolder<String>(binding.root){

        override fun bind(source: String) {
            val large = source.replace("thumbnail", "large")
            binding.src = large
            itemSelect(mList.indexOf(source))
        }
    }
}
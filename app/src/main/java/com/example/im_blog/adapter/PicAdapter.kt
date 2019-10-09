package com.example.im_blog.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.im_blog.databinding.PicViewBinding
import com.example.im_blog.ui.ImageSeeActivity

class PicAdapter(private val imagesClickEvent:(index:Int)->Unit): RecyclerView.Adapter<PicAdapter.ViewHolder>() {

    private val mList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PicViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun setList(list: List<String>) {
        mList.clear()
        mList.addAll(list)
    }

    inner class ViewHolder(val binging: PicViewBinding) : BaseHolder<String>(binging.root) {

        override fun bind(source: String) {
            val h = when (mList.size) {
                1 -> px2dp(itemView.context, 300)
                2 -> px2dp(itemView.context, 150)
                else -> px2dp(itemView.context, 100)
            }
            Glide.with(binging.image).load(source).override(h, h).into(binging.image)
            binging.image.setOnClickListener { imagesClickEvent(mList.indexOf(source)) }
        }
    }

    private fun px2dp(context: Context, dp: Int): Int {
        val d = context.resources.displayMetrics.density
        return (dp * d + 0.5).toInt()
    }
}
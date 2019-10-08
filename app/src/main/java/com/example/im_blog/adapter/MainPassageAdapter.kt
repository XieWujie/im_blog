package com.example.im_blog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.AsyncPagedListDiffer
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.*
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.CommentItemBinding
import com.example.im_blog.databinding.MainPassageContentItemBinding
import com.example.im_blog.http.entities.Comment

class MainPassageAdapter(private val passage:Passage):PagedListAdapter<Comment,RecyclerView.ViewHolder>(COMMENT_DIFF){


    override fun getItemViewType(position: Int): Int {
       return when(position){
            0-> TYPE_PASSAGE
           else-> TYPE_COMMENT
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()+1
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
      val holder =  when(viewType){
           TYPE_PASSAGE->{
               val binding = MainPassageContentItemBinding.inflate(inflater,parent,false)
               MainPassageHolder(binding)
           }
          TYPE_COMMENT->{
              val binding = CommentItemBinding.inflate(inflater,parent,false)
              CommentHolder(binding)
          }
          else->throw RuntimeException("type is not true")
       }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(position){
           0->(holder as MainPassageHolder).bind(passage)
           else->(holder as CommentHolder).bind(getItem(position)!!)
       }
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer,1))
    }

    companion object{
        private val COMMENT_DIFF = CommentDifferCallback()
        private const val TYPE_PASSAGE = 1
        private const val TYPE_COMMENT = 2
    }
}

package com.example.im_blog.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.im_blog.R
import com.example.im_blog.adapter.ImageAdapter
import com.example.im_blog.database.User.User
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.ActivityImageSeeBinding
import com.example.im_blog.http.entities.Comment

class ImageSeeActivity : AppCompatActivity() {


    private lateinit var binding:ActivityImageSeeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_image_see)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val snaphelper = LinearSnapHelper()
        snaphelper.attachToRecyclerView(binding.recyclerView)
        dispatch()
    }


    private fun dispatch(){
        val images = intent.getStringArrayExtra("images")
        binding.avatar = intent.getStringExtra("avatar")
        binding.name = intent.getStringExtra("name")
        binding.thumb = intent.getIntExtra("thumbCount",0)
        val index = intent.getIntExtra("index",0)
        if (index >0) {
            val layoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
                    layoutManager.scrollToPositionWithOffset(index, 0)
                    if (layoutManager.itemCount > 1) {
                        binding.recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                }
            }
            binding.recyclerView.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
        }
        val size = images.size
        val adapter = ImageAdapter(){
            binding.page.text = "${it+1}/$size"
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val position = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (position == -1){
                    return
                }
                binding.page.text = "${position+1}/$size"
            }
        })
        adapter.setList(images.toList())
        adapter.notifyDataSetChanged()
    }


    companion object{

        const val TYPE = "what_type"
        const val TYPE_PASSAGE = 1
        const val TYPE_COMMENT = 2
        fun launch(context: Context,type:Int,images:Array<String>,avatar:String,name:String,id:Long,thumbCount:Int,commentCount:Int,index:Int){
            val intent = Intent(context,ImageSeeActivity::class.java)
            intent.apply {
                putExtra(TYPE,type)
                putExtra("avatar",avatar)
                putExtra("name",name)
                putExtra("id",id)
                putExtra("thumbCount",thumbCount)
                putExtra("commentCount",commentCount)
                putExtra("images",images)
                putExtra("index",index)
            }
            context.startActivity(intent)
        }
    }
}

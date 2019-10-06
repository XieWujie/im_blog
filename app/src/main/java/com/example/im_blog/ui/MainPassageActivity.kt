package com.example.im_blog.ui

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im_blog.R
import com.example.im_blog.adapter.PassageAdapter
import com.example.im_blog.adapter.PicAdapter
import com.example.im_blog.base.App
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.ActivityMainPassageBinding
import com.example.im_blog.http.entities.Comments
import com.example.im_blog.http.entities.Passages
import com.google.gson.Gson
import org.kodein.di.generic.instance
import java.lang.RuntimeException

class MainPassageActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainPassageBinding
    private val adapter = PassageAdapter()
    private val gson: Gson by App.INSTANCE.kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_passage)
        val passage = intent.getSerializableExtra(passage_TAG)?:throw RuntimeException("the passage can not be null")
        passageLoad(passage as Passage)
    }

    fun passageLoad(source: Passage){
        binding.passage = source
        val pics = getPics(source.pic_urls)
        val span = when (pics.size) {
            1 -> 1
            2 -> 2
            else -> 3
        }
        binding.picRecyclerView.layoutManager = GridLayoutManager(this, span)
        val adapter = PicAdapter()
        binding.picRecyclerView.adapter = adapter
        adapter.setList(pics.toList())
        adapter.notifyDataSetChanged()
    }

    private fun getPics(pics: String): Array<String> {
        val g = gson.fromJson(pics, Array<String>::class.java)
        return g
    }


    companion object{

        private val passage_TAG = "passage"
        fun launch(context:Context,passage:Passage){
            val intent = Intent(context,MainPassageActivity::class.java)
            intent.putExtra(passage_TAG,passage)
            intent.putExtra(Passage.PASSAGE_ID,passage.id)
            context.startActivity(intent)
        }
    }


}

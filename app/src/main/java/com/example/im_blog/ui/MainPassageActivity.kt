package com.example.im_blog.ui

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.ViewTreeObserver
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.example.im_blog.R
import com.example.im_blog.adapter.MainPassageAdapter
import com.example.im_blog.adapter.PassageAdapter
import com.example.im_blog.adapter.PicAdapter
import com.example.im_blog.base.App
import com.example.im_blog.base.BaseActivity
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.ActivityMainPassageBinding
import com.example.im_blog.di.CommentModule
import com.example.im_blog.http.entities.Comments
import com.example.im_blog.http.entities.Passages
import com.example.im_blog.ui.comments.CommentsFragment
import com.example.im_blog.ui.comments.CommentsViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_items.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import java.lang.RuntimeException

class MainPassageActivity : BaseActivity(),KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        extend(parent)
        bind<MainPassageActivity>() with scoped(AndroidLifecycleScope).singleton { this@MainPassageActivity }
        import(CommentModule)
    }
    private lateinit var binding:ActivityMainPassageBinding
    private val model: CommentsViewModel by instance()
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_passage)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        loadData()
    }


    fun loadData(){
        val data = intent.getSerializableExtra(passage_TAG)?:throw RuntimeException("the passage can not be null")
        val first = intent.getIntExtra(WHAT_FIRST, CONTENT_FIRST)
        val passage = data as Passage
        val id = passage.id
        val adapter = MainPassageAdapter(passage)
        binding.recyclerView.adapter = adapter
        model.fetchComments(id).observe(this, Observer {
            adapter.submitList(it)
        })
        if (first == COMMENT_FIRST) {
            binding.recyclerView.viewTreeObserver.addOnGlobalLayoutListener(layoutListener)
        }
        model.error.observe(this, Observer {
            it.printStackTrace()
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->finish()
        }
        return true
    }

    private val layoutListener = object :ViewTreeObserver.OnGlobalLayoutListener{
        override fun onGlobalLayout() {
            layoutManager.scrollToPositionWithOffset(1, 0)
            if (layoutManager.itemCount > 1){
                binding.recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }
    }

    companion object{

        private val passage_TAG = "passage"
        const val CONTENT_FIRST = 0
        const val COMMENT_FIRST = 1
        const val WHAT_FIRST = "what_first"
        fun launch(context:Context,passage:Passage,first:Int){
            val intent = Intent(context,MainPassageActivity::class.java)
            intent.putExtra(passage_TAG,passage)
            intent.putExtra(WHAT_FIRST,first)
            context.startActivity(intent)
        }
    }


}

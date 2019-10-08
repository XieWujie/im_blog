package com.example.im_blog.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im_blog.R
import com.example.im_blog.adapter.CommentAdapter
import com.example.im_blog.base.BaseFragment
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.FragmentCommemtsBinding
import com.example.im_blog.di.CommentModule
import com.example.im_blog.http.entities.Comments
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton
import java.lang.RuntimeException

class CommentsFragment :BaseFragment(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        extend(parent)
        bind<CommentsFragment>() with scoped(AndroidLifecycleScope).singleton { this@CommentsFragment }
        import(CommentModule)
    }

    private val model:CommentsViewModel by instance()
    private lateinit var binding:FragmentCommemtsBinding
    private val adapter = CommentAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_commemts,container,false)
        val recyclerview = binding.recyclerView
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context)
        loadData()
        return binding.root
    }

    fun loadData(){
        val intent = activity?.intent?:return
        val passage = intent.getSerializableExtra("passage") as Passage
        val id = passage.id
        binding.passage = passage
        model.fetchComments(id).observe(this, Observer {
            adapter.submitList(it)
        })

        model.error.observe(this, Observer {
            it.printStackTrace()
        })
    }
}
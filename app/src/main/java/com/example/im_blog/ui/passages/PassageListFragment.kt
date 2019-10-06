package com.example.im_blog.ui.passages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.im_blog.R
import com.example.im_blog.adapter.PassageAdapter
import com.example.im_blog.base.BaseFragment
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.FragmentItemsBinding
import com.example.im_blog.di.mainFragmentModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

class PassageListFragment :BaseFragment(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        extend(parent)
        import(mainFragmentModule)
        bind<PassageListFragment>() with scoped(AndroidLifecycleScope).singleton { this@PassageListFragment }
    }

    private lateinit var binding:FragmentItemsBinding
    private val model:MainFragmentViewModel by instance()
    private val adapter = PassageAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_items, container,false)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        dispatchEvent()
        return binding.root
    }


    private fun dispatchEvent(){
        val passage_type = arguments?.getInt(Passage.PASSAGE_TYPE,Passage.TYPE_FLOWER)?:Passage.TYPE_FLOWER
        Log.d("type_passage","$passage_type")
        model.passages(passage_type).observe(this, Observer {
            adapter.submitList(it)
        })
        model.isLoading.observe(this, Observer {
            binding.fresh.isRefreshing = it
        })
        binding.fresh.setOnRefreshListener {
            model.fresh()
        }

        model.err.observe(this, Observer {
            it?.apply { it.message } })
    }
}
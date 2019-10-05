package com.example.im_blog.ui.me

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.im_blog.R
import com.example.im_blog.base.BaseFragment
import com.example.im_blog.database.passage.Passage
import com.example.im_blog.databinding.FragmentMeBinding
import com.example.im_blog.di.MineModule
import com.example.im_blog.repository.user.Mine
import com.example.im_blog.ui.passages.PassageListFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

class MeFragment :BaseFragment(),KodeinAware{
    override val kodein = Kodein.lazy {
        extend(parent)
        bind<MeFragment>() with scoped(AndroidLifecycleScope).singleton { this@MeFragment }
        import(MineModule)
    }

    private val model:MeViewModel by instance()
    private lateinit var binding:FragmentMeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_me,container,false)
        model.mine.observe(this, Observer {
            binding.mine = it
            Log.d("mineInfo",it.toString())
        })
        model.fetchMine()
        dispatchEvent()
        return binding.root
    }

    fun dispatchEvent(){
        binding.weiboText.setOnClickListener {
            it.findNavController().navigate(R.id.PassageListFragment,Bundle().let {
                it.putInt(Passage.PASSAGE_TYPE,Passage.TYPE_MINE)
                it })
        }
    }
}
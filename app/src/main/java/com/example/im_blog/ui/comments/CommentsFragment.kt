package com.example.im_blog.ui.comments

import com.example.im_blog.base.BaseFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class CommentsFragment :BaseFragment(),KodeinAware{

    override val kodein: Kodein = Kodein.lazy {
        extend(parent)
    }
}